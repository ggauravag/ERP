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
import com.dbt.forms.ManageEmployeeForm;

public class ManageEmployeeAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		System.out.println("Manage Employee Action called");
		ManageEmployeeForm manageEmployeeForm = (ManageEmployeeForm) form;

		int Empid = Integer.parseInt(manageEmployeeForm.getEmployeeID());
		String Empname = manageEmployeeForm.getEmpname();
		String Empemail = manageEmployeeForm.getEmployeeEmail();
		String Empmobile = manageEmployeeForm.getEmployeeMobile();
		String Empdoj = manageEmployeeForm.getEmployeeDoj();
		String Emphouse = manageEmployeeForm.getEmployeeHouse();
		String Empline1 = manageEmployeeForm.getEmployeeline1();
		String Empline2 = manageEmployeeForm.getEmployeeline2();
		String Empcity = manageEmployeeForm.getEmployeeCity();
		String Empstate = manageEmployeeForm.getEmployeeState();
		String Empzip = manageEmployeeForm.getEmployeeZip();
		String Emprole = manageEmployeeForm.getEmployeeRole();
		int Empsalary = Integer.parseInt(manageEmployeeForm.getEmployeeSal());
		int numID = manageEmployeeForm.getNumIDProof();

		System.out.println("Num id:" + numID);
		String dbProofpath = "";

		for (int temp = 1; temp <= numID; temp++) {
			FormFile proof = null;
			if (temp == 1) {
				proof = manageEmployeeForm.getProof1();
			} else if (temp == 2) {
				proof = manageEmployeeForm.getProof2();
			} else if (temp == 3) {
				proof = manageEmployeeForm.getProof3();
			} else if (temp == 4) {
				proof = manageEmployeeForm.getProof4();
			} else {

			}
			String type = proof.getFileName().substring(
					proof.getFileName().indexOf(".") + 1);
			dbProofpath = dbProofpath + "img/idproofs/" + "proof_" + temp + "_"
					+ Empid + "." + type + ";";
			String path = getServlet().getServletContext().getRealPath("/")
					+ "img/idproofs/" + "proof_" + temp + "_" + Empid + "."
					+ type;
			System.out.println("The path would be : " + path);

			FileOutputStream stream = new FileOutputStream(new File(path));
			stream.write(proof.getFileData());
			stream.flush();
			stream.close();

		}
		System.out.println("Proof path:" + dbProofpath);

		int i = new EmployeeDAO().updateEmployeeDetails(Empid, Empname,
				Empmobile, Empdoj, Empline1, Empline2, Emphouse, Empcity,
				Empstate, Empzip, Empsalary, Emprole, dbProofpath);
		if (i == 1) {
			result = "success";
			request.setAttribute("status", "success");
		} else {
			result = "failure";
			request.setAttribute("status", "failure");
		}
		return mapping.findForward(result);
	}
}
