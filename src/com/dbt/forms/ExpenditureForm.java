package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ExpenditureForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	String expType, amount, dateTime, expMode, description, paid, empName, receivedBy, dynamic;
	String empId;
	
	
	
	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getExpMode() {
		return expMode;
	}

	public void setExpMode(String expMode) {
		this.expMode = expMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	public String getDynamic() {
		return dynamic;
	}
	
	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}

	public String getReceivedBy() {
		return receivedBy;
	}
	
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	
	public String getempId() {
		return empId;
	}
	
	public void setempId(String empId) {
		this.empId = empId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		expType = request.getParameter("selectType");
		amount = request.getParameter("amount");
		expMode = request.getParameter("selectMode");
		description = request.getParameter("inputDesc");
		paid = request.getParameter("inputPaid");
		dynamic = request.getParameter("dynamic1");
		
		System.out.println("Exp Form : Type = " + expType);
		
		ActionErrors errors = new ActionErrors();
		if (expType == null || expType == "Select Type")
			errors.add("typeError", new ActionMessage("expType.blankError"));
		if (amount == null || amount == "")
			errors.add("amountError", new ActionMessage("amount.blankError"));
		if (expMode == null || expMode == "Select Mode")
			errors.add("modeError", new ActionMessage("expMode.blankError"));
		if (description == null || description.equals(""))
			errors.add("descriptionError", new ActionMessage("description.blankError"));
		if (paid == null || paid.equals(""))
			errors.add("paidError", new ActionMessage("paid.blankError"));
		if (dynamic == null || dynamic.equals(""))
			errors.add("dynamicError", new ActionMessage("dynamic.blankError"));
		

		if(expType.equals("salary"))
		{
			empId = request.getParameter("empId");
			receivedBy = request.getParameter("dynamic2");
			
			if (empId == null || empId.equals(""))
				errors.add("empIdError", new ActionMessage("empId.blankError"));
			if (receivedBy == null || receivedBy.equals(""))
				errors.add("receivedByError", new ActionMessage("receivedBy.blankError"));
		}
		
		return errors;
	}
}
