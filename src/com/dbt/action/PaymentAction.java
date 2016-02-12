package com.dbt.action;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.dbt.support.Email;

public class PaymentAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		PaymentForm payForm = (PaymentForm) form;
		String paidBy = payForm.getPaidBy();
		String medium = payForm.getModeType();
		int amount = payForm.getAmount();
		int orderId = payForm.getOrderID();
		String desc = payForm.getDescription();

		String dateTime = payForm.getPayTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		Date date = null;
		if (!"".equals(dateTime) && dateTime != null) {
			try {
				date = formatter.parse(dateTime);
			} catch (Exception e) {
				Email.sendExceptionReport(e);
				date = new Date();
			}
		} else {
			date = new Date();
		}

		if (medium.equals("PayUMoney Online Gateway")) {
			System.out.println("PayuMoney");
		}

		Payment payment = new PaymentDAO().makePayment(paidBy, amount, orderId,
				medium, desc,date, payForm.getDiscountAmount());
		//System.out.println("Payment ID is : "+payment.getId()+", amount : "+payment.getAmount());
		if (payment != null) {
			request.getSession().setAttribute("payment", payment);
			request.getSession().setAttribute("discount", payForm.getDiscountAmount());
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
