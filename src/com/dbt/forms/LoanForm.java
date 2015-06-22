package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoanForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String loanAmount;
	private String loanRate;
	private String loanInstallment;
	private String loanTenure;
	
	public String getLoanAmount() {
		return loanAmount;
	}
	
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}

	public String getLoanInstallment() {
		return loanInstallment;
	}

	public void setLoanInstallment(String loanInstallment) {
		this.loanInstallment = loanInstallment;
	}

	public String getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		loanAmount = request.getParameter("inputAmount");
		loanTenure = request.getParameter("inputTenure");
		loanRate = request.getParameter("inputInterest");
		loanInstallment = request.getParameter("inputInstallment");
		
		if (loanAmount == null || loanAmount == "")
			errors.add("loanAmountError", new ActionMessage("loanAmount.blankError"));
		if (loanTenure == null || loanTenure == "")
			errors.add("loanTenureError", new ActionMessage("loanTenure.blankError"));
		if (loanRate == null || loanRate == "")
			errors.add("loanRate", new ActionMessage("loanRate.blankError"));
		if (loanInstallment == null || loanInstallment == "")
			errors.add("loanInstallment", new ActionMessage("loanInstallment.blankError"));
		
		return errors;
	}
}
