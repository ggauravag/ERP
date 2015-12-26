package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.PurchaseDAO;
import com.dbt.forms.DeletePurchaseForm;

public class DeletePurchase extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";

		int purchseID = ((DeletePurchaseForm) form).getPurchaseID();
		if (purchseID != 0) {
			if (new PurchaseDAO().deletePurchase(purchseID)) {
				request.setAttribute("status", true);
			} else {
				request.setAttribute("status", false);
			}
		}
		return mapping.findForward(result);
	}

}
