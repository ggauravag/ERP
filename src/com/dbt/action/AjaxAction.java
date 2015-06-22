package com.dbt.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.dao.ComplaintDAO;
import com.dbt.dao.CustomerDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.data.Order;
import com.dbt.data.Product;
import com.dbt.support.DBTSms;
import com.dbt.support.Email;

public class AjaxAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		System.out.println("AjaxActionServlet: service(): action = " + action);

		if ("getCustomerDetails".equals(action)) {
			getCustomerDetails(request, response);
		}

		if ("sendOrderDetails".equals(action)) {
			sendOrderDetails(request, response);
		}

		if ("getProductByCategory".equals(action)) {
			getProductsByCategory(request, response);
		}
		 
		if("getOrderByNameIDMobile".equals(action)){
			getOrderByNameIDMobile(request, response);
		}

		return null;
	}

	
	public void getOrderByNameIDMobile(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String name = request.getParameter("name");
		String oid = request.getParameter("order");
		String mob = request.getParameter("mobile");
		String responseText = "";
		//System.out.println("AjaxActionServlet : "+name+oid+mob);
		
		List<Order> order = ComplaintDAO.getOrderDetails(name,oid,mob);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray custArray = new JSONArray();
		while (iter.hasNext()) 
		{
		 Order o = iter.next();
		 
			 JSONObject ord = new JSONObject();
			 
			 ord.put("oid", o.getId());
			 ord.put("amount", o.getAmount());
			 ord.put("date", o.getDate());
			 ord.put("time", o.getTime());
		    
			        JSONObject custom = new JSONObject();
			        Customer cus = o.getCustomer();
			        custom.put("name", cus.getName());
			        custom.put("mobile", cus.getMobile());
			        custom.put("email", cus.getEmail());
		
		  ord.put("Customer",custom);
		 custArray.add(ord);
		}
		list.put("orderdetails", custArray);
		responseText = list.toJSONString();
		//System.out.println("AjaxActionServletFromRequestComplaint : Response JSON is : "+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}
	
	
	public void getCustomerDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String responseText = "";
		List<Customer> customers = CustomerDAO.getCustomer(name);
		Iterator<Customer> iter = customers.iterator();
		JSONObject list = new JSONObject();
		JSONArray productsArray = new JSONArray();
		while (iter.hasNext()) {
			Customer p = iter.next();
			JSONObject product = new JSONObject();
			product.put("id", p.getId());
			product.put("name", p.getName());
			product.put("email", p.getEmail());
			product.put("mobile", p.getMobile());
			product.put("tin", p.getTin());
			product.put("type", p.getType());
			JSONObject address = new JSONObject();
			Address add = p.getAddress();
			address.put("house_no", add.getHouseNo());
			address.put("line1", add.getLine1());
			address.put("line2", add.getLine2());
			address.put("city", add.getCity());
			address.put("state", add.getState());
			address.put("zip", add.getZip());

			product.put("address", address);

			productsArray.add(product);
		}
		list.put("customers", productsArray);
		responseText = list.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);

	}

	private void sendOrderDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String mobileReg = "^[7-9]{1}[0-9]{9}$";

		String emails = request.getParameter("email");
		String mobiles = request.getParameter("mobile");
		String[] email = emails.split(",");
		String[] mobile = mobiles.split(",");

		for (int i = 0; i < email.length; i++) {
			if (!EmailValidator.getInstance().isValid(email[i]))
				email[i] = null;
		}

		for (int i = 0; i < mobile.length; i++) {
			if (!mobile[i].matches(mobileReg))
				mobile[i] = null;
		}

		Order order = (Order) request.getSession().getAttribute("order");
		Email.sendOrderDetails(email, order);
		DBTSms.sendOrderSMS(mobile, order);

		response.getWriter().write("{ \"status\":\"success\" }");

	}

	public void getProductsByCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String responseText = "";
		int catgId = Integer.parseInt(request.getParameter("catgId"));
		List<Product> products = OrderDAO.getProducts(catgId);
		Iterator<Product> iter = products.iterator();
		JSONObject list = new JSONObject();
		JSONArray productsArray = new JSONArray();
		while (iter.hasNext()) {
			Product p = iter.next();
			JSONObject product = new JSONObject();
			product.put("id", p.getId());
			product.put("name", p.getName());
			product.put("quantity", p.getQuantity());
			product.put("sell_price", p.getSellPrice());
			product.put("category", p.getCategory());
			product.put("cost_price", p.getCostPrice());
			productsArray.add(product);
		}
		list.put("products", productsArray);
		responseText = list.toJSONString();
		System.out.println("AjaxAction : Response JSON is : " + responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

}
