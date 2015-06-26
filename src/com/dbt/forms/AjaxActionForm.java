package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class AjaxActionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String action, catgid, email, mobile, name;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCatgid() {
		return catgid;
	}

	public void setCatgid(String catgid) {
		this.catgid = catgid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		action = request.getParameter("action");
		ActionErrors errors = new ActionErrors();
		if ("getCustomerDetails".equals(action)) {

		} else if ("sendOrderDetails".equals(action)) {

		} else if ("getProductByCategory".equals(action)) {

		} else if ("getOrderByNameIDMobile".equals(action)) {

		} else if ("getFirmsDetails".equals(action)) {

		} else if ("setFirm".equals(action)) {

		} else if ("getOrder".equals(action)) {

		} else if ("sendShipmentDetails".equals(action)) {

		} else {
			errors.add("error", new ActionMessage("request.invalid"));
		}

		return errors;
	}
}
