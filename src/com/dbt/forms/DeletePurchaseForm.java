package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class DeletePurchaseForm extends ActionForm {

	int purchaseID;

	public int getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
		if(purchaseID == 0)
		{
			errors.add("purchaseError", new ActionMessage("purchase.blankError"));
		}
		return errors;
	}
}
