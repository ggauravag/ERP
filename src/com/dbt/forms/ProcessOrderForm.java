package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProcessOrderForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int orderID;
	int[] prodID;
	String mediumType,vehicleNum,mediumName,contactMedium,shipDate;
	JSONArray items;
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub 
		ActionErrors errors = new ActionErrors();
		
		String orderid = request.getParameter("orderID");
		String[] prodid = request.getParameterValues("prodID");
		mediumName = request.getParameter("mediumName");
		vehicleNum = request.getParameter("vehicleNum");
		mediumType = request.getParameter("mediumType");
		contactMedium = request.getParameter("contactMedium");
		shipDate = request.getParameter("shipDate");
		String json = request.getParameter("itemJSON");
		try {
			items = (JSONArray)new JSONParser().parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ProcessOrderForm JSON is : "+json);
		
		if(orderid == null || "".equals(orderid))
			errors.add("orderError", new ActionMessage("order.blankError"));
		else
			orderID = Integer.parseInt(orderid);
		if(prodid == null || prodid.length == 0)
			errors.add("productError", new ActionMessage("product.blankError"));
		else
		{
			for(int i = 0; i < prodid.length; i++)
				prodID[i] = Integer.parseInt(prodid[i]);
		}
		if(mediumName == null || "Select Transport Medium".equals(mediumName))
			errors.add("mediumError", new ActionMessage("medium.blankError"));
		if(shipDate == null || "".equals(shipDate))
			errors.add("dateError", new ActionMessage("date.blankError"));
		return errors;
	}

	public JSONArray getItems() {
		return items;
	}

	public void setItems(JSONArray items) {
		this.items = items;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int[] getProdID() {
		return prodID;
	}

	public void setProdID(int[] prodID) {
		this.prodID = prodID;
	}

	public String getMediumType() {
		return mediumType;
	}

	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getContactMedium() {
		return contactMedium;
	}

	public void setContactMedium(String contactMedium) {
		this.contactMedium = contactMedium;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
}
