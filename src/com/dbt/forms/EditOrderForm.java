package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EditOrderForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	 
	String[] productNames, productPrices, productQtys;
	String numProd, orderId, customerId, deleteOrder;
	
	public String[] getProductNames() {
		return productNames;
	}
	public void setProductNames(String[] productNames) {
		this.productNames = productNames;
	}
	public String[] getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(String[] productPrices) {
		this.productPrices = productPrices;
	}
	public String[] getProductQtys() {
		return productQtys;
	}
	public void setProductQtys(String[] productQtys) {
		this.productQtys = productQtys;
	}
	public String getNumProd() {
		return numProd;
	}
	public void setNumProd(String numProd) {
		this.numProd = numProd;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDeleteOrder() {
		return deleteOrder;
	}
	public void setDeleteOrder(String deleteOrder) {
		this.deleteOrder = deleteOrder;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		orderId = request.getParameter("orderId");
		deleteOrder = request.getParameter("deleteOrder");
		
		ActionErrors errors = new ActionErrors();
		
		if (orderId == null || orderId == "")
			errors.add("orderidError", new ActionMessage("orderId.blankError"));
		
		if("delete".equals(deleteOrder))
		{
			System.out.println("At EditOrderForm, Deletion request for orderId : " + orderId);
		}
		else
		{
			numProd = request.getParameter("numProd");
			customerId = request.getParameter("customerId");
			
			if(Integer.parseInt(numProd) != 0)
			{
				System.out.println("At EditOrderForm, numProd != 0");
				productNames = request.getParameterValues("prodName");
				productPrices = request.getParameterValues("prodPrice");
				productQtys = request.getParameterValues("prodQty");
				
				if (productNames == null || productPrices == null || productQtys == null)
					errors.add("productError", new ActionMessage("product.blankError"));
			}
			System.out.println("At EditOrderForm, numProd = 0");
			System.out.println("NumProd : " + numProd + ",CustomerId : " + customerId + ",OrderId : " + orderId);
			if (customerId == null || customerId == "")
				errors.add("customeridError", new ActionMessage("customerId.blankError"));
		}
		
		return errors;
	}
}
