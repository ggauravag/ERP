package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ReturnForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035712780242021600L;
	boolean returnProd[];
	int returnQty[];
	int orderID;
	int numProd;
	int returnAmount;
	int amount;
	int[] prodID;
	String action,modeType,description,paidBy;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int[] getProdID() {
		return prodID;
	}

	public void setProdID(int[] prodID) {
		this.prodID = prodID;
	}

	public int getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(int returnAmount) {
		this.returnAmount = returnAmount;
	}

	public int getNumProd() {
		return numProd;
	}

	public void setNumProd(int numProd) {
		this.numProd = numProd;
	}

	public boolean[] getReturnProd() {
		return returnProd;
	}

	public void setReturnProd(boolean[] returnProd) {
		this.returnProd = returnProd;
	}

	public int[] getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(int[] returnQty) {
		this.returnQty = returnQty;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		ActionErrors errors = new ActionErrors();
		System.out.println("Return Product ? " + returnProd);
		if (getAction().equals("return")) {
			if (returnProd == null || returnProd.length == 0) {
				System.out.println("Return Prod is null");
				errors.add("returnError", new ActionMessage(
						"returnProduct.blankError"));
			}
			if (returnQty == null || returnQty.length == 0) {
				System.out.println("Return Qty is null");
				errors.add("returnError", new ActionMessage(
						"returnProduct.blankError"));
			}
			if (orderID == 0) {
				System.out.println("Order ID is null");
				errors.add("returnError", new ActionMessage(
						"orderID.blankError"));
			}
		}
		else if(getAction().equals("payment"))
		{
			if(modeType == null || modeType.equals("Select Payment Mode"))
				errors.add("mediumError", new ActionMessage("medium.blankError"));
			if(amount == 0)
				errors.add("amountError", new ActionMessage("amount.blankError"));
		}

		return errors;
	}

}
