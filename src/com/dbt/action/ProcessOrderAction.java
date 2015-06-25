package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.OrderProcessDAO;
import com.dbt.forms.ProcessOrderForm;

public class ProcessOrderAction extends Action
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "failure";
		ProcessOrderForm orderform = (ProcessOrderForm)form;
		int[] prodID = orderform.getProdID();
		int orderID = orderform.getOrderID();
		String mediumType = orderform.getMediumType();
		String mediumName = orderform.getMediumName();
		String shipNum = orderform.getVehicleNum();
		String shipdate = orderform.getShipDate();
		String contact = orderform.getContactMedium();
		
		new OrderProcessDAO().shipProducts(orderID,prodID,mediumType, mediumName, shipNum, contact, shipdate);
		
		return mapping.findForward(result);
	}
}
