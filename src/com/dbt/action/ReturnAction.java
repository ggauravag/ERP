package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.PaymentDAO;
import com.dbt.dao.ReturnDAO;
import com.dbt.forms.ReturnForm;

public class ReturnAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		ReturnForm returnForm = (ReturnForm) form;

		String result = "success";

		if ("return".equals(returnForm.getAction())) {
			int[] returnQty = returnForm.getReturnQty();
			int orderID = returnForm.getOrderID();
			int[] productID = returnForm.getProdID();
			int returnAmount = returnForm.getReturnAmount();
			int[] prodID;
			int[] qty;
			int i = 0;

			System.out.println("Number of returned items are : "
					+ returnQty.length);

			for (int a : returnQty) {
				if (a != 0) {
					i++;
				}
			}
			prodID = new int[i];
			qty = new int[i];
			i = 0;
			for (int a : returnQty) {
				if (a != 0) {
					qty[i] = returnQty[i];
					prodID[i] = productID[i];
					i++;
				}

			}

			if (new ReturnDAO().returnProducts(orderID, prodID, qty)) {
				int payableAmount = new PaymentDAO().getPayableReturnAmount(
						orderID, returnAmount);

				if (payableAmount != 0) {
					request.getSession().setAttribute("payAmount",
							payableAmount);
					request.getSession().setAttribute("orderID", orderID);
					result = "confirm";
				} else {
					request.getSession().setAttribute("status", "success");
					result = "success";
				}
			} else {
				result = "failure";
				request.setAttribute("status", "failure");
			}
		} else if ("payment".equals(returnForm.getAction())) {
			if(new ReturnDAO().processPayment(returnForm.getOrderID(),
					returnForm.getAmount(), returnForm.getModeType() + ";"
							+ returnForm.getDescription(),
					returnForm.getPaidBy()))
			{
				request.getSession().setAttribute("status", "success");
				result = "success";
			}
		}

		return mapping.findForward(result);
	}
}
