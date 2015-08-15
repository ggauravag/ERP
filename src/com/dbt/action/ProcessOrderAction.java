package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;

import com.dbt.dao.OrderDAO;
import com.dbt.dao.OrderProcessDAO;
import com.dbt.dao.ShipmentDAO;
import com.dbt.forms.ProcessOrderForm;
import com.dbt.vo.Shipment;

public class ProcessOrderAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "success";
		ProcessOrderForm orderform = (ProcessOrderForm) form;
		int[] prodID = orderform.getProdID();
		int orderID = orderform.getOrderID();
		String mediumType = orderform.getMediumType();
		String mediumName = orderform.getMediumName();
		String shipNum = orderform.getVehicleNum();
		String shipdate = orderform.getShipDate();
		String contact = orderform.getContactMedium();
		JSONArray items = orderform.getItems();
		int discount = orderform.getDiscount();
		int shipCharge = orderform.getShippingCharge();
		new OrderDAO().addDiscount(orderID, discount);
		Shipment s = new OrderProcessDAO().shipProducts(items, orderID, prodID,
				mediumType, mediumName, shipNum, contact, shipdate, shipCharge);
		s = new ShipmentDAO().getShipmentByID(s.getId());
		if (s == null || s.getItems().size() == 0) {
			result = "failure";
		}
		HttpSession session = request.getSession();
		session.setAttribute("shipment", s);
		session.setAttribute("orderID", orderID);
		session.setAttribute("mobile", request.getParameter("mobile"));
		session.setAttribute("email", request.getParameter("email"));
		return mapping.findForward(result);
	}
}
