package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class OrderForm extends ActionForm {

	String name, email, house, line1, line2, city, state, mobile, zip, numProd;
	String[] productNames, productPrices, productQtys;
	String custID,type,tin,orderTime;
	String custType;
	
	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getHouse() {
		return house;
	}

	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getMobile() {
		return mobile;
	}

	public String getZip() {
		return zip;
	}

	public String getNumProd() {
		return numProd;
	}

	public String[] getProductNames() {
		return productNames;
	}

	public String[] getProductPrices() {
		return productPrices;
	}

	public String[] getProductQtys() {
		return productQtys;
	}

	public String getMobileReg() {
		return mobileReg;
	}

	String mobileReg = "^[7-9]{1}[0-9]{9}$";

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		name = request.getParameter("custName");
		email = request.getParameter("custEmail");
		house = request.getParameter("custHouseNo");
		line1 = request.getParameter("custAdd1");
		line2 = request.getParameter("custAdd2");
		city = request.getParameter("custCity");
		state = request.getParameter("custState");
		zip = request.getParameter("custPinCode");
		mobile = request.getParameter("custMobile");
		custID = request.getParameter("custID");
		type = request.getParameter("type");
		tin = request.getParameter("tin");

		productNames = request.getParameterValues("prodName");
		productPrices = request.getParameterValues("prodPrice");
		productQtys = request.getParameterValues("prodQty");
		numProd = request.getParameter("numProd");
		System.out.println("OrderForm Called : "+name+", Order Time is : "+orderTime+" TIN is : "+tin);

		ActionErrors errors = new ActionErrors();
		if (name == null || name == "")
			errors.add("nameError", new ActionMessage("name.blankError"));
		if (house == null || house == "")
			errors.add("houseError", new ActionMessage("house.blankError"));
		if (line1 == null || line1 == "")
			errors.add("line1Error", new ActionMessage("address.blankError"));
		if (city == null || city.equals("Select City"))
			errors.add("cityError", new ActionMessage("city.blankError"));
		if (state == null || state.equals("Select State"))
			errors.add("stateError", new ActionMessage("state.blankError"));
		if (zip == null || zip == "")
			errors.add("zipError", new ActionMessage("pin.blankError"));
		if (mobile == null || mobile == "")
			errors.add("mobileError", new ActionMessage("mobile.blankError"));
		if (numProd == null || numProd == "0" || productNames == null
				|| productPrices == null || productQtys == null)
			errors.add("productError", new ActionMessage("product.blankError"));
		if (mobile != null && !mobile.matches(mobileReg))
			errors.add("mobileError", new ActionMessage("mobile.invalidError"));
		if (!"".equals(email) && !EmailValidator.getInstance().isValid(email))
			errors.add("emailError", new ActionMessage("email.invalidError"));

		return errors;
	}

	public String getCustID() {
		return custID;
	}

}
