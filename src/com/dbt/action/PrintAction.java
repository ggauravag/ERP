package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.OrderDAO;
import com.dbt.data.Order;
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
				int id = Integer.parseInt(oid);
				Order d = new Order();
				request.setAttribute("order", d);
				result = "printOrder";
			}
		} else if ("challan".equals(printType)) {
			Shipment shipment = (Shipment)request.getSession().getAttribute("s");
			String orderID = request.getSession().getAttribute("orderID").toString();
			Order order = new OrderDAO().getOrder(Integer.parseInt(orderID));
			//System.out.println("Products are : "+order.getProducts().size());
			request.setAttribute("order", order);
			request.setAttribute("shipment", shipment);
			result = "printChallan";
		}

		return mapping.findForward(result);
	}
}
