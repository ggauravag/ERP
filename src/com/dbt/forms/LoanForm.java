package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoanForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private int inputAmount;
	private int inputTenure;
	private int inputInstallment;
	private double inputInterest;
	
	
	
	public int getInputAmount() {
		return inputAmount;
	}

	public void setInputAmount(int inputAmount) {
		this.inputAmount = inputAmount;
	}

	public int getInputTenure() {
		return inputTenure;
	}

	public void setInputTenure(int inputTenure) {
		this.inputTenure = inputTenure;
	}

	public int getInputInstallment() {
		return inputInstallment;
	}

	public void setInputInstallment(int inputInstallment) {
		this.inputInstallment = inputInstallment;
	}

	public double getInputInterest() {
		return inputInterest;
	}

	public void setInputInterest(double inputInterest) {
		this.inputInterest = inputInterest;
	}

	
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if (inputAmount == 0)
			errors.add("loanAmountError", new ActionMessage("loanAmount.blankError"));
		if (inputTenure == 0)
			errors.add("loanTenureError", new ActionMessage("loanTenure.blankError"));
		if (inputInterest == 0.0)
			errors.add("loanRate", new ActionMessage("loanRate.blankError"));
		if (inputInstallment == 0)
			errors.add("loanInstallment", new ActionMessage("loanInstallment.blankError"));
		
		return errors;
	}
}
