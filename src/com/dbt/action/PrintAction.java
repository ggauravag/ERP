package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.DocumentDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.dao.PaymentDAO;
import com.dbt.dao.ShipmentDAO;
import com.dbt.data.Invoice;
import com.dbt.data.Merchant;
import com.dbt.data.Order;
import com.dbt.data.Payment;
import com.dbt.support.Email;
import com.dbt.vo.Shipment;

public class PrintAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		String printType = request.getParameter("print");
		String result = "";
		if ("order".equals(printType)) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				result = "error";
			} else {
				Order order = (Order) session.getAttribute("order");
				request.setAttribute("order", order);
				result = "printOrder";
			}
		} else if ("view".equals(printType)) {
			String oid = request.getParameter("oid");
			if (oid != null) {
				Order d = new OrderDAO().getOrder(Integer.parseInt(oid));
				request.setAttribute("order", d);
				result = "printOrder";
			}
		} else if ("letterHead".equals(printType)) {

			result = "printLetter";

		} else if ("challan".equals(printType)) {
			String shipID = request.getParameter("shipID");
			String orderID = request.getParameter("orderID");
			Shipment shipment = null;
			Order order = null;
			Merchant merchant = (Merchant) request.getSession().getAttribute(
					"merchant");

			if (shipID != null && orderID != null) {
				shipment = new ShipmentDAO().getShipmentByID(Integer
						.parseInt(shipID));
			} else {
				shipment = (Shipment) request.getSession().getAttribute(
						"shipment");
				orderID = request.getSession().getAttribute("orderID")
						.toString();
			}
			int challanID = new DocumentDAO().getPrintableID(merchant.getId(),
					"CHALLAN", shipment.getId());
			shipment.setChallanId(challanID);

			order = new OrderDAO().getOrder(Integer.parseInt(orderID));
			// System.out.println("Products are : "+order.getProducts().size());
			request.setAttribute("order", order);
			request.setAttribute("shipment", shipment);
			result = "printChallan";
		} else if ("receipt".equals(printType)) {
			String paymentID = request.getParameter("payID");
			Merchant merchant = (Merchant) request.getSession().getAttribute(
					"merchant");

			if (paymentID != null && !"".equals(paymentID)) {
				Payment payment = new PaymentDAO().getPaymentByID(Integer
						.parseInt(paymentID));
				int receiptID = new DocumentDAO().getPrintableID(
						merchant.getId(), "RECEIPT",
						Integer.parseInt(paymentID));
				payment.setReceiptId(receiptID);
				request.getSession().setAttribute("payment", payment);
			} else {
				Payment payment = (Payment) request.getSession().getAttribute(
						"payment");
				if (payment != null) {
					int receiptID = new DocumentDAO().getPrintableID(
							merchant.getId(), "RECEIPT", payment.getId());
					payment.setReceiptId(receiptID);
					request.getSession().setAttribute("payment", payment);
				}
			}
			result = "printReceipt";
		} else if ("orderPrint".equals(printType)) {
			String orderID = request.getParameter("orderID");
			if (orderID != null) {
				int id = Integer.parseInt(orderID);
				Order order = new OrderDAO().getOrder(id);
				request.setAttribute("order", order);
				result = "printOrder";
			}
		} else if ("invoice".equals(printType)) {
			String orderID = request.getParameter("orderID");
			String vatApp = request.getParameter("vatApplicable");
			String vat = request.getParameter("vatPercent");
			String date = request.getParameter("billDate");
			String sendInvoice = request.getParameter("sendInvoiceOnEmail");
			Invoice invoice = new Invoice();
			invoice.setPrintDate(date);
			Merchant merchant = (Merchant) request.getSession().getAttribute(
					"merchant");
			Order order = null;
			if (orderID != null) {
				int id = Integer.parseInt(orderID);
				int invoiceID = new DocumentDAO().getPrintableID(
						merchant.getId(), "INVOICE", id);
				invoice.setId(invoiceID);
				order = new OrderDAO().getOrder(id);
				invoice.setOrder(order);
				result = "printInvoice";
			}
			if (vatApp.equals("true")) {
				invoice.setHasVat(true);
				invoice.setVatPercent(Double.parseDouble(vat));
			} else
				invoice.setHasVat(false);

			if ("true".equals(sendInvoice))
				new Email().sendInvoice(new String[] { order.getCustomer()
						.getEmail() }, invoice, order, merchant);

			request.setAttribute("invoice", invoice);
		}

		return mapping.findForward(result);
	}
}
