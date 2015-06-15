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
		System.out.println("User email : " + email + ", password : " + password);
		ActionErrors errors = new ActionErrors();
		boolean isEmailEmpty = false;
		
		boolean isPasswordEmpty = false;
		if (email == null || email.trim().length() == 0) {
			isEmailEmpty = true;
		}
		if (password == null || password.trim().length() == 0) {
			isPasswordEmpty = true;
		}

		if (rememberMe != null) {
			System.out.println("User wants to be remebered, and email : "
					+ email + ", password : " + password);
		}

		boolean isEmailValid = EmailValidator.getInstance().isValid(email);
		if (isEmailEmpty) {
			errors.add("emailError", new ActionMessage("email.blankError"));
		} else if (!isEmailValid) {
			errors.add("emailError", new ActionMessage("email.invalidError"));
		}

		if (isPasswordEmpty) {
			errors.add("passwordError",
					new ActionMessage("password.blankError"));
		}

		return errors;
	}
}
