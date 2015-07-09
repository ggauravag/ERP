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
	int orderID, amount;
	String mode, paidBy, description;

	public String getDescription() {
		return description;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getAmount() {
		return amount;
	}

	public String getMode() {
		return mode;
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
		String orderID = request.getParameter("orderID");
		String modeType = request.getParameter("modeType");
		String amount = request.getParameter("amount");
		String paidBy = request.getParameter("paidBy");
		String description = request.getParameter("description");

		if (orderID == null || "".equals(orderID)) {
			errors.add("orderError", new ActionMessage("order.blankError"));
		} else {
			this.orderID = Integer.parseInt(orderID);
		}

		if (description != null || !"".equals(description)) {
			this.description = description;
		}

		if (paidBy == null || "".equals(paidBy)) {
			errors.add("paidByError", new ActionMessage("paidBy.blankError"));
		} else {
			this.paidBy = paidBy;
		}

		if (modeType == null || "".equals(modeType)) {
			errors.add("mediumError", new ActionMessage("mode.blankError"));
		} else {
			this.mode = modeType;
		}

		if (amount == null || "".equals(amount)) {
			errors.add("amountError", new ActionMessage("amount.blankError"));
		} else {
			this.amount = Integer.parseInt(amount);
		}

		return errors;
	}
}
