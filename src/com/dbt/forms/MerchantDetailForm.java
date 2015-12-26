package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class MerchantDetailForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	String merchantId, amount, expMode, description, paid, currentAmount, addPayment;
	
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getAddPayment() {
		return addPayment;
	}

	public void setAddPayment(String addPayment) {
		this.addPayment = addPayment;
	}

	public String getmerchantId() {
		return merchantId;
	}

	public void setmerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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
	
	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
	
		amount = request.getParameter("amount");
		currentAmount = request.getParameter("currentPayment");
		expMode = request.getParameter("selectMode");
		description = request.getParameter("inputDesc");
		paid = request.getParameter("inputPaid");
		merchantId = request.getParameter("selectMerchant");
		
		
		System.out.println("MerchantDetailForm : Merchant Id : " + merchantId);
		
		ActionErrors errors = new ActionErrors();
		if("Yes".equals(getAddPayment()))
		{
			if (merchantId == null || merchantId == "")
				errors.add("merchantError", new ActionMessage("merchantId.blankError"));
			if (amount == null || amount == "")
				errors.add("amountError", new ActionMessage("amount.blankError"));
			if (currentAmount == null || currentAmount == "")
				errors.add("currentAmountError", new ActionMessage("currentAmount.blankError"));
			if (expMode == null || expMode == "Select Mode")
				errors.add("modeError", new ActionMessage("expMode.blankError"));
			/*if (description == null || description.equals(""))
				errors.add("descriptionError", new ActionMessage("description.blankError"));*/
			if (paid == null || paid.equals(""))
				errors.add("paidError", new ActionMessage("paid.blankError"));
		}
		return errors;
	}
}
