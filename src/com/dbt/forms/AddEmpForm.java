package com.dbt.forms;

import javax.activation.MimeType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class AddEmpForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fname;
	private String lname;
	private String email;
	private String mobile;
	private String role;
	private String salary;
	private String timeType;
	private String stime;
	private String etime;
	private String doj;
	private String house;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zip;
	private FormFile profilePicture;

	String mobileReg = "^[7-9]{1}[0-9]{9}$";

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();

		System.out.println("Name:" + fname + " " + lname + ",Email:" + email
				+ ",Mobile:" + mobile + ",Role:" + role + ",Salary:" + salary
				+ ",TimeType:" + timeType + ",Start Time:" + stime
				+ ",End Time:" + etime + ",DOJ:" + doj + ",Address:" + house
				+ "," + line1 + "," + line2 + "," + state + "," + city + ","
				+ zip);

		if (fname == null || fname.trim().length() == 0 || lname == null
				|| lname.trim().length() == 0) {
			errors.add("nameError", new ActionMessage("name.blankError"));
		}

		if (!"OTHER".equals(role)) {
			if (email == null || email.trim().length() == 0)
				errors.add("emailError", new ActionMessage("email.blankError"));
			if (email != null && !EmailValidator.getInstance().isValid(email))
				errors.add("emailError",
						new ActionMessage("email.invalidError"));

			if (profilePicture == null)
				errors.add("profilePicError", new ActionMessage("profilePic.blankError"));
			else {
				System.out.println("Picture Uploaded");
				String mimeType = profilePicture.getContentType();
				if (!mimeType.startsWith("image/")) {
					errors.add("", new ActionMessage(""));
				}
				System.out.println("Size : " + profilePicture.getFileSize()
						+ "," + profilePicture.getContentType());
				System.out.println("Name : " + profilePicture.getFileName());
			}
		}
		
		if (mobile == null || mobile.trim().length() == 0 || "".equals(mobile))
			errors.add("mobileError", new ActionMessage("mobile.blankError"));
		/*
		 * if(mobile != null && !mobile.matches(mobileReg))
		 * errors.add("mobileError", new ActionMessage("mobile.invalidError"));
		 */
		if (role == null || role.trim().length() == 0)
			errors.add("roleError", new ActionMessage("role.blankError"));
		if (salary == null || salary.trim().length() == 0)
			errors.add("salaryError", new ActionMessage("salary.invalidError"));

		if (!"OTHER".equals(role)) {
			if (timeType.equals("SPECIFIC")) {
				if (stime == null || etime == null)
					errors.add("timeError",
							new ActionMessage("time.blankError"));
			}

		}

		if (doj == null || doj.toString().trim().length() == 0)
			errors.add("dateError", new ActionMessage("date.blankError"));

		System.out.println("No. of errors : " + errors.size());
		return errors;
	}

	public FormFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(FormFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getMobileReg() {
		return mobileReg;
	}

	public void setMobileReg(String mobileReg) {
		this.mobileReg = mobileReg;
	}

}
