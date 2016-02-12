package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class PaymentForm extends ActionForm {
	/**
	 * 
	 */
	int orderID, amount, discountAmount;
	String modeType, paidBy, description, payTime;
	
	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getDescription() {
		return description;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getAmount() {
		return amount;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -8787931777458878781L;

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();

		if (orderID == 0) {
			errors.add("orderError", new ActionMessage("order.blankError"));
		}

		if (paidBy == null || "".equals(paidBy)) {
			errors.add("paidByError", new ActionMessage("paidBy.blankError"));
		}

		if (modeType == null || "".equals(modeType)) {
			errors.add("mediumError", new ActionMessage("mode.blankError"));
		}

		if (amount == 0) {
			errors.add("amountError", new ActionMessage("amount.blankError"));
		}

		return errors;
	}
}
