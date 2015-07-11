package com.dbt.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dbt.dao.ComplaintDAO;
import com.dbt.dao.CustomerDAO;
import com.dbt.dao.MerchantDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.dao.PaymentDAO;
import com.dbt.dao.ProductDAO;
import com.dbt.dao.ShipmentDAO;
import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.data.Merchant;
import com.dbt.data.Order;
import com.dbt.data.Order_item;
import com.dbt.data.Payment;
import com.dbt.data.Product;
import com.dbt.data.User;
import com.dbt.support.DBTSms;
import com.dbt.support.Email;
import com.dbt.vo.Shipment;

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

		if ("getOrderByNameIDMobile".equals(action)) {
			getOrderByNameIDMobile(request, response);
		}

		if ("getFirmsDetails".equals(action)) {
			getFirmsDetails(request, response);
		}

		if ("sendShipmentDetails".equals(action)) {
			sendShipmentDetails(request, response);
		}

		if ("getUserName".equals(action)) {
			getUserName(request, response);
		}

		if ("setFirm".equals(action)) {
			setFirm(request, response);
		}
		if ("getOrder".equals(action)) {
			getOrder(request, response);
		}

		if ("checkStock".equals(action)) {
			checkStock(request, response);
		}

		if ("getPaymentDetails".equals(action)) {
			getPaymentDetails(request, response);
		}

		if ("sendReceiptDetails".equals(action)) {
			sendReceiptDetails(request, response);
		}

		if ("getShipmentDetails".equals(action)) {
			getShipmentDetails(request, response);
		}

		return null;
	}

	public void getShipmentDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String orderID = request.getParameter("orderID");
		JSONObject responseJSON = new JSONObject();
		JSONArray shipmentArray = new JSONArray();
		if (orderID != null && !"".equals(orderID)) {
			responseJSON.put("status", "success");
			int orderId = Integer.parseInt(orderID);
			List<Shipment> shipments = new ShipmentDAO().getShipment(orderId);
			for (int i = 0; i < shipments.size(); i++) {
				Shipment shipment = shipments.get(i);
				JSONObject shipmentJSON = new JSONObject();
				shipmentJSON.put("id", shipment.getId());
				shipmentJSON.put("medium", shipment.getMedium());
				shipmentJSON.put("mediumName", shipment.getMediumName());
				shipmentJSON.put("contact", shipment.getContact());
				shipmentJSON.put("mediumNumber", shipment.getMediumNumber());
				shipmentJSON.put("time", shipment.getPrintableTime());
				JSONArray productArray = new JSONArray();
				List<Product> products = shipment.getItems();
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					JSONObject productJSON = new JSONObject();
					productJSON.put("id", product.getId());
					productJSON.put("name", product.getName());
					productJSON.put("quantity", product.getQuantity());
					productJSON.put("sellPrice", product.getSellPrice());
					productJSON.put("category", product.getCategory());
					productArray.add(productJSON);
				}
				shipmentJSON.put("products", productArray);
				shipmentArray.add(shipmentJSON);
			}
			responseJSON.put("shipments", shipmentArray);
		} else {
			responseJSON.put("status", "failure");
		}

		String responseText = responseJSON.toJSONString();
		response.setContentType("text/json");
		System.out.println(responseText);
		response.getWriter().write(responseText);
	}

	private void sendReceiptDetails(HttpServletRequest request,
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

		Payment payment = (Payment) request.getSession()
				.getAttribute("payment");

		Order order = (Order) request.getSession().getAttribute("order");
		Email.sendReceiptDetails(email, payment, order);
		DBTSms.sendReceiptSMS(mobile, payment, order);

		response.getWriter().write("{ \"status\":\"success\" }");
	}

	private void getPaymentDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String orderID = request.getParameter("orderID");
		if (orderID != null) {
			int order = Integer.parseInt(orderID);
			Iterator<Payment> payments = new PaymentDAO().getPayment(order)
					.iterator();
			JSONArray paymentArray = new JSONArray();
			while (payments.hasNext()) {
				Payment payment = payments.next();
				JSONObject paymentJSON = new JSONObject();
				paymentJSON.put("id", payment.getId());
				paymentJSON.put("amount", payment.getAmount());
				paymentJSON.put("mode", payment.getMode());
				paymentJSON.put("type", payment.getType());
				paymentJSON.put("paidBy", payment.getPaidBy());
				paymentJSON.put("orderID", payment.getOrderId());
				paymentJSON
						.put("datetime", payment.getDatetime().toGMTString());
				paymentArray.add(paymentJSON);
			}
			JSONObject resp = new JSONObject();
			resp.put("status", "success");
			resp.put("payments", paymentArray);
			String responseText = resp.toJSONString();
			response.setContentType("text/json");
			System.out.println(responseText);
			response.getWriter().write(responseText);
		}
	}

	private void checkStock(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String productID = request.getParameter("productID");
		String prodQty = request.getParameter("prodQty");

		if (prodQty != null && productID != null) {
			int prodID = Integer.parseInt(productID);
			int qty = Integer.parseInt(prodQty);
			Product product = new ProductDAO().getProductQuantity(prodID);
			JSONObject productJSON = new JSONObject();
			productJSON.put("id", product.getId());
			productJSON.put("quantity", product.getQuantity());
			productJSON.put("name", product.getName());
			productJSON.put("sellPrice", product.getSellPrice());
			productJSON.put("costPrice", product.getCostPrice());
			productJSON.put("status", "success");
			String responseText = productJSON.toJSONString();
			response.setContentType("text/json");
			response.getWriter().write(responseText);
		} else {
			response.setContentType("text/json");
			response.getWriter().write("{\"status\":\"error\"}");
		}
	}

	private void getUserName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		JSONObject userJSON = new JSONObject();
		if (user == null) {
			userJSON.put("name", "");
			userJSON.put("session", false);
		} else {
			userJSON.put("name", user.getFirstName() + " " + user.getLastName());
			userJSON.put("session", true);
			userJSON.put("lastlog", user.getLogip());
		}
		String responseText = userJSON.toJSONString();
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	private void sendShipmentDetails(HttpServletRequest request,
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

		Shipment shipment = (Shipment) request.getSession().getAttribute(
				"shipment");
		int orderid = Integer.parseInt(request.getSession()
				.getAttribute("orderID").toString());
		Order order = new OrderDAO().getOrder(orderid);
		Email.sendShipment(email, shipment, order);
		DBTSms.sendShipmentSMS(mobile, shipment, order);

		response.getWriter().write("{ \"status\":\"success\" }");

	}

	public void getOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String oid = request.getParameter("order");
		String mob = request.getParameter("mobile");
		String responseText = "";
		// System.out.println("AjaxActionServlet : "+name+oid+mob);

		List<Order> order = OrderDAO.getOrderDetails(oid, name, mob);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray custArray = new JSONArray();
		while (iter.hasNext()) {
			Order o = iter.next();
			JSONObject ord = new JSONObject();
			ord.put("oid", o.getId());
			ord.put("amount", o.getAmount());
			ord.put("date", o.getDatetime().toGMTString());
			JSONArray itemArray = new JSONArray();

			List<Order_item> items = o.getOrderitems();
			for (int i = 0; i < items.size(); i++) {
				Order_item itemJava = items.get(i);
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("order_id", itemJava.getOrder_id());
				itemJSON.put("product_id", itemJava.getProduct_id());
				itemJSON.put("product_name", itemJava.getProduct_name());
				itemJSON.put("product_qty", itemJava.getQuantity());
				itemJSON.put("product_amount", itemJava.getAmount());
				itemJSON.put("ship_id", itemJava.getShip_id());
				itemArray.add(itemJSON);
			}
			ord.put("items", itemArray);
			JSONObject custom = new JSONObject();
			Customer cus = o.getCustomer();
			custom.put("name", cus.getName());
			custom.put("mobile", cus.getMobile());
			custom.put("email", cus.getEmail());
			ord.put("Customer", custom);
			custArray.add(ord);
		}
		list.put("orderdetails", custArray);
		responseText = list.toJSONString();
		System.out
				.println("AjaxActionServletFromRequestComplaint : Response JSON is : "
						+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void setFirm(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String firm = request.getParameter("firmString");
		int fid = Integer.parseInt(request.getParameter("firm"));
		System.out.println(firm + " || " + fid);
		JSONParser parser = new JSONParser();
		try {
			JSONObject firmObj = (JSONObject) parser.parse(firm);
			JSONObject addObj = (JSONObject) firmObj.get("address");
			Address address = new Address(addObj.get("houseNo").toString(),
					addObj.get("line1").toString(), addObj.get("line2")
							.toString(), addObj.get("city").toString(), addObj
							.get("state").toString(), addObj.get("zip")
							.toString());
			Merchant merchant = new Merchant(Integer.parseInt(firmObj.get("id")
					.toString()), firmObj.get("name").toString(), address,
					firmObj.get("tin").toString(), firmObj.get("mobile")
							.toString(), firmObj.get("email").toString(),
					firmObj.get("logo").toString());
			request.getSession().setAttribute("merchant", merchant);
			response.setContentType("text/json");
			response.getWriter().write("{ \"Response\":\"Success\" }");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setContentType("text/json");
			response.getWriter().write("{ \"Response\":\"Error\" }");
		}
	}

	public void getFirmsDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String mobiles = request.getParameter("mobile");
		List<Merchant> merchants = new MerchantDAO().getFirms(mobiles);
		JSONObject resp = new JSONObject();
		JSONArray mer = new JSONArray();
		Iterator<Merchant> iterator = merchants.iterator();
		while (iterator.hasNext()) {
			Merchant merchant = iterator.next();
			JSONObject singleMer = new JSONObject();
			singleMer.put("id", merchant.getId());
			singleMer.put("name", merchant.getName());
			singleMer.put("mobile", merchant.getMobile());
			singleMer.put("email", merchant.getEmail());
			singleMer.put("tin", merchant.getTin());
			singleMer.put("logo", merchant.getLogo());
			JSONObject addressJSON = new JSONObject();
			Address add = merchant.getAddress();
			addressJSON.put("houseNo", add.getHouseNo());
			addressJSON.put("line1", add.getLine1());
			addressJSON.put("line2", add.getLine2());
			addressJSON.put("city", add.getCity());
			addressJSON.put("state", add.getState());
			addressJSON.put("zip", add.getZip());
			singleMer.put("address", addressJSON);
			mer.add(singleMer);
		}
		resp.put("firms", mer);
		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet Firms - Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getOrderByNameIDMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String oid = request.getParameter("order");
		String mob = request.getParameter("mobile");
		String responseText = "";
		// System.out.println("AjaxActionServlet : "+name+oid+mob);

		List<Order> order = ComplaintDAO.getOrderDetails(name, oid, mob);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray custArray = new JSONArray();
		while (iter.hasNext()) {
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
			ord.put("Customer", custom);
			custArray.add(ord);
		}
		list.put("orderdetails", custArray);
		responseText = list.toJSONString();
		System.out
				.println("AjaxActionServletFromRequestComplaint : Response JSON is : "
						+ responseText);
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
