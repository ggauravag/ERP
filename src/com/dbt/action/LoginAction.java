package com.dbt.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.LoginDAO;
import com.dbt.data.User;
import com.dbt.forms.LoginForm;
import com.dbt.support.AESCrypto;
import com.dbt.support.DBTSms;

public class LoginAction extends Action {

	public static final String inputFormat = "HH:mm:ss";

	private Date date;
	private Date dateCompareOne;
	private Date dateCompareTwo;

	private String compareStringOne;
	private String compareStringTwo;

	SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat);

	/**
	 * @return
	 */
	private boolean compareDates() {

		boolean isinTime = false;

		Date d = new Date();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(d.getHours());
		stringBuilder.append(":");
		stringBuilder.append(d.getMinutes());
		stringBuilder.append(":00");
		date = parseDate(stringBuilder.toString());
		dateCompareOne = parseDate(compareStringOne);
		dateCompareTwo = parseDate(compareStringTwo);

		if (dateCompareOne.equals(dateCompareTwo)) {
			return true;
		}

		if (dateCompareOne.before(date) && dateCompareTwo.after(date)) {
			// yada yada
			isinTime = true;
		}
		// System.out.println("Dates are : " + dateCompareOne.toString() + ","
		// + date.toString() + "," + dateCompareTwo.toString());
		return isinTime;
	}

	private Date parseDate(String date) {

		try {
			return inputParser.parse(date);
		} catch (java.text.ParseException e) {
			return new Date(0);
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward otpVerify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String otp = (String) session.getAttribute("otp");
		String inputOtp = request.getParameter("otp");
		String result = "failure";
		System.out.println("LoginAction : Received OTP - " + inputOtp
				+ ", actual is - " + otp);
		if (inputOtp.equals(otp)) {
			session.removeAttribute("otp");
			result = "success";
		}

		return mapping.findForward(result);
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		String method = request.getParameter("method");
		if (method != null && method.equals("otpVerify")) {
			return otpVerify(mapping, form, request, response);
		}

		LoginForm loginForm = (LoginForm) form;

		String email = loginForm.getEmail();
		String password = loginForm.getPassword();
		String remember = loginForm.getRememberMe();

		String logip = request.getRemoteAddr();
		HttpSession session = request.getSession(true);

		User user = LoginDAO.login(email, password);
		String result = "failure";

		if (user != null) {
			LoginDAO.updateLogIp(user, logip);
			session.setAttribute("user", user);
			result = "success";
			compareStringOne = user.getSt_time().toString();
			compareStringTwo = user.getEnd_time().toString();

			if (user.getStatus().equals("N")) {
				result = "failure";
				request.setAttribute("error", "status.invalid");
				System.out.println("LoginAction : Status is not verified");
			}

			if (result.equals("success") && remember != null
					&& remember.equals("Yes")) {
				Cookie cookie = new Cookie("auth_token", AESCrypto.encrypt(user
						.getEmail() + ":" + user.getPassword()));
				cookie.setMaxAge(15 * 24 * 60 * 60);
				response.addCookie(cookie);
			}

			if (result.equals("success") && !compareDates()) {
				// System.out.println("Request not in time !");
				User owner = LoginDAO.getOwner();
				String otp = DBTSms.sendOwnerOTP(owner.getMobile(),
						user.getFirstName() + " " + user.getLastName());
				session.setAttribute("otp", otp);
				System.out.println("LoginAction : OTP is - " + otp);
				result = "failure";
			}

		} else {
			request.setAttribute("error", "login.invalid");
		}

		return mapping.findForward(result);
	}
}
