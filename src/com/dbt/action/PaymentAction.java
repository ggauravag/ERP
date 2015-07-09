package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.OrderDAO;
import com.dbt.dao.PaymentDAO;
import com.dbt.data.Order;
import com.dbt.data.Payment;
import com.dbt.forms.PaymentForm;

public class PaymentAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		PaymentForm payForm = (PaymentForm) form;
		String paidBy = payForm.getPaidBy();
		String medium = payForm.getMode();
		int amount = payForm.getAmount();
		int orderId = payForm.getOrderID();
		String desc = payForm.getDescription();

		if (medium.equals("PayUMoney Online Gateway")) {
			System.out.println("PayuMoney");
		}

		Payment payment = new PaymentDAO().makePayment(paidBy, amount, orderId,
				medium, desc);
		if (payment != null) {
			request.getSession().setAttribute("payment", payment);
			request.getSession().setAttribute("status", "success");
			Order order = new OrderDAO().getOrder(payment.getOrderId());
			request.getSession().setAttribute("order", order);
		} else {
			request.getSession().setAttribute("status", "failure");
			result = "failure";
		}

		return mapping.findForward(result);
	}
}
