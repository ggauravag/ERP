package com.dbt.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.dbt.dao.EmployeeDAO;
import com.dbt.forms.AddEmpForm;
import com.dbt.support.Email;
import com.dbt.support.Utils;

public class AddEmpAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		int i = 0;
		// System.out.println("AddEmpAction called");
		AddEmpForm addEmpform = (AddEmpForm) form;

		String fname = addEmpform.getFname();
		String lname = addEmpform.getLname();
		String email = addEmpform.getEmail();
		String mobile = addEmpform.getMobile();
		mobile = mobile.replace("-", "");
		String role = addEmpform.getRole();
		String salary = addEmpform.getSalary();
		String doj = addEmpform.getDoj();
		String timeType = addEmpform.getTimeType();
		String house = addEmpform.getHouse();
		String line1 = addEmpform.getLine1();
		String line2 = addEmpform.getLine2();
		String city = addEmpform.getCity();
		String state = addEmpform.getState();
		String zip = addEmpform.getZip();

		System.out.println("AddEmpAction: Values Retrieved fname:" + fname
				+ ",lname:" + lname + ",email:" + email + ",mobile:" + mobile
				+ ",role:" + role + ",salary:" + salary + ",doj:" + doj
				+ ",:Address" + house + "," + line1 + "," + line2 + "," + state
				+ "," + city + "," + zip);

		if (role.equals("OTHER")) {
			// System.out.println("AddEmpAction: Inside If of Other");
			i = new EmployeeDAO().insertEmployeeOther(fname, lname, email,
					mobile, salary, doj, house, line1, line2, city, state, zip);
		} else {
			String stime = "09:00 AM";
			String etime = "10:00 PM";

			if (timeType.equals("SPECIFIC")) {
				stime = addEmpform.getStime();
				etime = addEmpform.getEtime();
				System.out.println("Starting Time is:" + stime
						+ ",Ending time is:" + etime);

			}
			String ip = request.getRemoteAddr();
			String password = Utils.getPassword();
			
			String encryptedPass = Utils.getMD5(password);
			
			System.out.println("AddEmpAction: Inside Else if of Other");
			i = new EmployeeDAO().insertEmployee(fname, lname, email, mobile,
					salary, doj, role, encryptedPass, ip, stime, etime, house, line1,
					line2, city, state, zip);
			new Email().sendEmployeeEmail(fname + " " + lname, role, email, password);
		}
		int j = 1;
		if (!role.equals("OTHER") && i != 0) {
			j = 0;
			FormFile profile = addEmpform.getProfilePicture();
			String type = profile.getFileName().substring(profile.getFileName().indexOf(".")+1);
			String databasePath = "img/profile-pics/" + "Employee" + i + "."
					+ type;
			String path = getServlet().getServletContext().getRealPath("/")
					+ databasePath;
			System.out.println("The path would be : " + path);

			FileOutputStream stream = new FileOutputStream(new File(path));
			stream.write(profile.getFileData());
			stream.flush();
			stream.close();

			j = new EmployeeDAO().insertPhoto(i, databasePath);

		}
		System.out.println("Value of i is:" + i);

		if (i != 0 && j != 0) {
			request.setAttribute("status", "success");
		} else {
			System.out.println("Else part called of i value:" + i);
			request.setAttribute("status", "failure");
			result = "failure";
		}

		return mapping.findForward(result);
	}
}
