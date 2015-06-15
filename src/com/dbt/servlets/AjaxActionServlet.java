package com.dbt.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.dao.CustomerDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.data.Product;

/**
 * Servlet implementation class AjaxActionServlet
 */
public class AjaxActionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(AjaxActionServlet.class);
	
	public void getCustomerDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
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
	

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AjaxActionServlet: service()");

		String responseText = "";
		String action = request.getParameter("action");
		System.out.println("AjaxActionServlet: service(): action = " + action);
		
		if("getCustomerDetails".equals(action))
		{
			getCustomerDetails(request,response);
			return;
		}
		
		
		
		if ("getProductByCategory".equals(action)) {
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
		}

		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

}
