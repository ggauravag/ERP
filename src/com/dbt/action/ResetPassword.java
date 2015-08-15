package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.LoginDAO;
import com.dbt.forms.ForgotPassword;
import com.dbt.support.Utils;

public class ResetPassword extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		ForgotPassword forgotForm = (ForgotPassword) form;

		if ("resetPassword".equals(forgotForm.getAction())) {
			String name = new LoginDAO().isValidToken(
					forgotForm.getAuthToken(),
					Utils.getMD5(forgotForm.getNewPassword()));
			if (name == null || name.length() == 0) {
				request.setAttribute("status", false);
				request.setAttribute("authToken", forgotForm.getAuthToken());
				result = "failure";
			} else {
				request.getSession().setAttribute("forgotName", name);
			}
			System.out.println("ResetPassword Called, result is  "+result+","+name);
			
		}

		return mapping.findForward(result);
	}

}
