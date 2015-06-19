package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CapitalForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String capitalAmount;
	private String capitalRate;
	private String capitalLender;
	
	public String getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(String capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	public String getCapitalRate() {
		return capitalRate;
	}
	public void setCapitalRate(String capitalRate) {
		this.capitalRate = capitalRate;
	}
	public String getCapitalLender() {
		return capitalLender;
	}
	public void setCapitalLender(String capitalLender) {
		this.capitalLender = capitalLender;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
	
		ActionErrors errors = new ActionErrors();
		capitalAmount = request.getParameter("inputAmount");
		capitalLender = request.getParameter("inputLender");
		capitalRate = request.getParameter("inputRate");
		
		if (capitalAmount == null || capitalAmount == "")
			errors.add("capitalAmountError", new ActionMessage("capitalAmount.blankError"));
		if (capitalLender == null || capitalLender == "")
			errors.add("capitalLenderError", new ActionMessage("capitalLender.blankError"));
		if (capitalRate == null || capitalRate == "")
			errors.add("capitalRate", new ActionMessage("capitalRate.blankError"));
		
		return errors;
	}
	
}
