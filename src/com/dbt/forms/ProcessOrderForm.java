package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dbt.support.Email;

public class ProcessOrderForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int orderID;
	int[] prodID;
	String mediumType, vehicleNum, mediumName, contactMedium, shipDate;
	JSONArray items;
	int discount, shippingCharge;

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();

		String json = request.getParameter("itemJSON");
		try {
			items = (JSONArray) new JSONParser().parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		}
		System.out.println("ProcessOrderForm JSON is : " + json);

		if (orderID == 0)
			errors.add("orderError", new ActionMessage("order.blankError"));

		if (prodID == null || prodID.length == 0)
			errors.add("productError", new ActionMessage("product.blankError"));

		if (mediumName == null || "Select Transport Medium".equals(mediumName))
			errors.add("mediumError", new ActionMessage("medium.blankError"));

		if (shipDate == null || "".equals(shipDate))
			errors.add("dateError", new ActionMessage("date.blankError"));
		return errors;
	}

	public int getDiscount() {
		return discount;
	}

	public int getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(int shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
