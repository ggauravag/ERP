package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class StockForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	String category, numProd;
	String[] productNames, productCPs, productQtys, productSPs, productIds;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String[] getProductNames() {
		return productNames;
	}
	public void setProductNames(String[] productNames) {
		this.productNames = productNames;
	}
	public String[] getProductCPs() {
		return productCPs;
	}
	public void setProductCPs(String[] productCPs) {
		this.productCPs = productCPs;
	}
	public String[] getProductQtys() {
		return productQtys;
	}
	public void setProductQtys(String[] productQtys) {
		this.productQtys = productQtys;
	}
	public String[] getProductSPs() {
		return productSPs;
	}
	public void setProductSPs(String[] productSPs) {
		this.productSPs = productSPs;
	}
	public String[] getProductIds() {
		return productIds;
	}
	public String getNumProd() {
		return numProd;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
	
		category = request.getParameter("selectCat");
		productNames = request.getParameterValues("inputProdName");
		productCPs = request.getParameterValues("prodCP");
		productSPs = request.getParameterValues("prodSP");
		productQtys = request.getParameterValues("inputProdQty");
		productIds = request.getParameterValues("productId");
		numProd = request.getParameter("numProd");
		
		ActionErrors errors = new ActionErrors();
		System.out.println("StockForm Called : " + category);
		
		if (category == null || category == "")
			errors.add("categoryError", new ActionMessage("category.blankError"));
		if (numProd == null || numProd == "0" || productNames == null || productIds == null ||
				productSPs == null || productCPs == null || productQtys == null)
			errors.add("productError", new ActionMessage("product.blankError"));
		
		return errors;
	}
}
