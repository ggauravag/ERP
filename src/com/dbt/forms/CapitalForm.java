package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CapitalForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	public String getInputLender() {
		return inputLender;
	}
	public void setInputLender(String inputLender) {
		this.inputLender = inputLender;
	}

	private int inputAmount;
	private double inputRate;
	private String inputLender;
	
	
	
	public double getInputRate() {
		return inputRate;
	}
	public void setInputRate(double inputRate) {
		this.inputRate = inputRate;
	}
	public int getInputAmount() {
		return inputAmount;
	}
	public void setInputAmount(int inputAmount) {
		this.inputAmount = inputAmount;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
	
		ActionErrors errors = new ActionErrors();
		
		System.out.println("Amount is : "+inputAmount+","+inputRate+","+inputLender);
		
		if (inputAmount == 0)
			errors.add("capitalAmountError", new ActionMessage("capitalAmount.blankError"));
		if (inputLender == null || "".equals(inputLender))
			errors.add("capitalLenderError", new ActionMessage("capitalLender.blankError"));
		if (inputRate == 0.0)
			errors.add("capitalRate", new ActionMessage("capitalRate.blankError"));
		
		return errors;
	}
	
}
