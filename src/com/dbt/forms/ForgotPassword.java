package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ForgotPassword extends ActionForm 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String newPassword;
	private String confirmPassword;
	private String action;
	private String authToken;
	
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
		
		if("resetPassword".equals(getAction()))
		{
			if(newPassword == null || newPassword.trim().length() == 0)
			{
				errors.add("confirmPasswordError", new ActionMessage("password.blankError"));
			}
			if(confirmPassword == null || confirmPassword.trim().length() == 0)
			{
				errors.add("newPasswordError", new ActionMessage("password.blankError"));
			}
			if(authToken == null || authToken.trim().length() == 0)
			{
				errors.add("newPasswordError", new ActionMessage("authToken.blankError"));
			}
		}
		System.out.println("ForgotPassword - Form action : "+newPassword+","+authToken);
		
		return errors;
	}
}
