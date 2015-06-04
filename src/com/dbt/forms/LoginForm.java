package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm 
{
	
	private String email;
	private String password;
	private String rememberMe;
	
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
		
		ActionErrors errors = new ActionErrors();
		
		if(email.length()==0 || password.length() == 0)
		{
			
		}
		
		
		boolean isValid = EmailValidator.getInstance().isValid(email);
		
		
		return super.validate(mapping, request);
		
		
	}
}
