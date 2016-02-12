package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
	
		ActionErrors errors = new ActionErrors();
		
		action = request.getParameter("action");
		if ("getCustomerDetails".equals(action)) {

		} else if ("sendOrderDetails".equals(action)) {

		} else if ("getProductByCategory".equals(action)) {

		} else if ("getOrderByNameIDMobile".equals(action)) {

		} else if ("getFirmsDetails".equals(action)) {

		} else if ("setFirm".equals(action)) {

		} else if ("getOrder".equals(action)) {

		} else if ("getUserName".equals(action)) {

		} else if ("sendShipmentDetails".equals(action)) {

		} else if ("getPaymentDetails".equals(action)) {

		} else if ("checkStock".equals(action)) {

		} else if ("sendReceiptDetails".equals(action)) {

		} else if ("getShipmentDetails".equals(action)) {

		} else if ("getExpenditureDetail".equals(action)) {

		} else if ("getEmployeeDetails".equals(action)) {

		} else if ("addCategory".equals(action)) {

		} else if ("addNewProduct".equals(action)) {

		} else if ("addNewMerchant".equals(action)) {

		} else if ("getComplaintByOIDCIDMobile".equals(action)) {

		} else if ("getEmpDetails".equals(action)) {

		} else if ("getCities".equals(action)) {

		} else if ("getAttendanceDetails".equals(action)) {

		} else if ("getPurchaseData".equals(action)) {

		} else if ("getReturnDetails".equals(action)) {

		} else if ("getEmployeesDetails".equals(action)) {

		} else if ("getCustomerByNameMob".equals(action)) {

		} else if ("getPurchaseDetail".equals(action)) {

		} else if ("getPurchaseById".equals(action)) {

		} else if ("getProductsByOrderId".equals(action)) {

		} else if ("deleteOrderedProductById".equals(action)) {

		} else if ("setReminder".equals(action)) {

		} else if ("getFeedback".equals(action)) {

		} else if ("getReminder".equals(action)) {

		} else if ("getEmployeeAttendance".equals(action)) {

		} else if ("getTransaction".equals(action)) {

		} else if("getExpenditureById".equals(action)) {
			
		} else if("getPaymentById".equals(action)){
			
		} else if("getOrderAndPurchase".equals(action)){
			
		} else if("removeDocument".equals(action)){
			
		} else if("getDocumentsById".equals(action)){
			
		} else if("addCityInState".equals(action)){
			
		}
		else {
			errors.add("error", new ActionMessage("request.invalid"));
		}

		return errors;
	}
}
