package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String rememberMe;
	private String action;
	private String otp;
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out
				.println("User email : " + email + ", password : " + password);
		ActionErrors errors = new ActionErrors();
		
		if("login".equals(getAction()))
		{
			if (email == null || email.trim().length() == 0) {
				errors.add("emailError", new ActionMessage("email.blankError"));
			}
			if (password == null || password.trim().length() == 0) {
				errors.add("passwordError",
						new ActionMessage("password.blankError"));
			}

			if (rememberMe != null) {
				System.out.println("User wants to be remebered, and email : "
						+ email + ", password : " + password);
			}

			boolean isEmailValid = EmailValidator.getInstance().isValid(email);
			if (!isEmailValid) {
				errors.add("emailError", new ActionMessage("email.invalidError"));
			}
			
			request.setAttribute("login", true);
		}
		else if("forgotPassword".equals(getAction()))
		{
			if (email == null || email.trim().length() == 0) {
				errors.add("forgotEmailError", new ActionMessage("email.blankError"));
			}
			if (!EmailValidator.getInstance().isValid(email)) {
				errors.add("forgotEmailError", new ActionMessage("email.invalidError"));
			}
			
			request.setAttribute("forgetPassword", true);
		}
		else if("otpVerify".equals(getAction()))
		{
			if(otp == null || otp.trim().length() < 6)
			{
				errors.add("otpBlank", new ActionMessage("otp.blankError"));
			}
			
			request.setAttribute("login", true);
		}
		
		
		return errors;
	}
}
