package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ManageCustomerForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	String buttonType, name, email, mob, type, house, line1, line2, city,
			state, mobile, pin, tin;
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	String mobileReg = "^[7-9]{1}[0-9]{9}$";

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		name = request.getParameter("inputName");
		email = request.getParameter("inputEmail");
		mobile = request.getParameter("inputMobile");
		tin = request.getParameter("tin");
		line1 = request.getParameter("custAdd1");
		line2 = request.getParameter("custAdd2");
		house = request.getParameter("custHouseNo");
		pin = request.getParameter("custPinCode");
		city = request.getParameter("custCity");
		state = request.getParameter("custState");
		type = request.getParameter("customerType");
		buttonType = request.getParameter("buttonType");
		id = Integer.parseInt(request.getParameter("customerId"));

		System.out.println(email + mobile + tin + line1 + line2 + house + pin
				+ city + state + id + type);

		System.out.println("Manage Customer Form : Name = " + name);

		ActionErrors errors = new ActionErrors();
		if (name == null || name == "")
			errors.add("nameError", new ActionMessage("name.blankError"));
		if (house == null || house == "")
			errors.add("houseError", new ActionMessage("house.blankError"));
		if (line1 == null || line1 == "")
			errors.add("line1Error", new ActionMessage("address.blankError"));
		if (buttonType.equals("")) {
			if (city == null || city.equals("Select City"))
				errors.add("cityError", new ActionMessage("city.blankError"));
			if (state == null || state.equals("Select State"))
				errors.add("stateError", new ActionMessage("state.blankError"));
		}
		if (pin == null || pin == "")
			errors.add("zipError", new ActionMessage("pin.blankError"));
		if (id == 0)
			errors.add("idError", new ActionMessage("id.blankError"));
		if (mobile == null || mobile == "")
			errors.add("mobileError", new ActionMessage("mobile.blankError"));
		if (mobile != null && !mobile.matches(mobileReg))
			errors.add("mobileError", new ActionMessage("mobile.invalidError"));
		if (!"".equals(email)
				&& !EmailValidator.getInstance().isValid(email))
			errors.add("emailError", new ActionMessage("email.invalidError"));
		if (type.equals("MERCHANT")) {
			if(!"".equals(tin) && tin.length() != 11)
				errors.add("tinError",new ActionMessage("tin.invalidError"));
		}
		return errors;
	}
}
