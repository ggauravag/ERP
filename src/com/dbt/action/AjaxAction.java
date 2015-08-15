package com.dbt.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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

import sun.font.EAttribute;

import com.dbt.dao.AttendanceDAO;
import com.dbt.dao.ComplaintDAO;
import com.dbt.dao.CustomerDAO;
import com.dbt.dao.EmployeeDAO;
import com.dbt.dao.ExpenditureDAO;
import com.dbt.dao.MerchantDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.dao.PaymentDAO;
import com.dbt.dao.ProductDAO;
import com.dbt.dao.PurchaseDAO;
import com.dbt.dao.ReturnDAO;
import com.dbt.dao.ShipmentDAO;
import com.dbt.dao.StockDAO;
import com.dbt.data.Address;
import com.dbt.data.Capital;
import com.dbt.data.Complaint;
import com.dbt.data.Customer;
import com.dbt.data.Employee;
import com.dbt.data.Loan;
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

		if ("getExpenditureDetail".equals(action)) {
			getExpenditureDetail(request, response);
		}

		if ("getEmployeeDetails".equals(action)) {
			getEmployeeDetails(request, response);
		}

		if ("addCategory".equals(action)) {
			addCategory(request, response);
		}

		if ("addNewProduct".equals(action)) {
			addNewProduct(request, response);
		}

		if ("addNewMerchant".equals(action)) {
			addNewMerchant(request, response);
		}
		if ("getComplaintByOIDCIDMobile".equals(action)) {
			getComplaintByOIDCIDMobile(request, response);
		}
		if ("getAttendanceDetails".equals(action)) {
			getAttendanceDetails(request, response);
		}
		if ("getEmpDetails".equals(action)) {
			System.out.println("Ajax called");
			getEmpDetails(request, response);
		}

		if ("getCities".equals(action)) {
			getCities(request, response);
		}

		if ("getPurchaseData".equals(action)) {
			getPurchaseData(request, response);
		}

		if ("getReturnDetails".equals(action)) {
			getReturnDetails(request, response);
		}

		if ("getEmployeesDetails".equals(action)) {
			getEmployeesDetails(request, response);
		}

		if ("getCustomerByNameMob".equals(action)) {
			getCustomerByNameMob(request, response);
		}

		if ("getPurchaseDetail".equals(action)) {
			getPurchaseDetail(request, response);
		}
		
		if("getPurchaseById".equals(action)){
			getPurchaseById(request, response);
		}

		return null;
	}

	public void getPurchaseById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
	}
	
	public void getPurchaseDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String pid = request.getParameter("pid");
		String to = request.getParameter("to");
		String from = request.getParameter("from");
		String name = request.getParameter("name");
		int purchaseID = 0;
		if (pid != null && pid.length() != 0) {
			purchaseID = Integer.parseInt(pid);
		}

		JSONArray purchaseJSON = new PurchaseDAO().getPurchaseDetail(name,
				from, to, purchaseID);
		JSONObject resp = new JSONObject();
		resp.put("purchases", purchaseJSON);
		resp.put("status", true);
		String responseText = resp.toJSONString();
		System.out.println(responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getCustomerByNameMob(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");

		String responseText = "";
		List<Customer> customers = new CustomerDAO().getCustomersByNameMob(
				name, mobile);
		Iterator<Customer> iter = customers.iterator();
		JSONObject list = new JSONObject();
		JSONArray customersArray = new JSONArray();
		while (iter.hasNext()) {
			Customer p = iter.next();
			JSONObject c = new JSONObject();
			c.put("id", p.getId());
			c.put("name", p.getName());
			c.put("email", p.getEmail());
			c.put("mobile", p.getMobile());
			c.put("tin", p.getTin());
			c.put("type", p.getType());
			JSONObject address = new JSONObject();
			Address add = p.getAddress();
			address.put("house_no", add.getHouseNo());
			address.put("line1", add.getLine1());
			address.put("line2", add.getLine2());
			address.put("city", add.getCity());
			address.put("state", add.getState());
			address.put("zip", add.getZip());

			c.put("address", address);

			customersArray.add(c);
		}
		list.put("customers", customersArray);
		responseText = list.toJSONString();
		// logger.info("getCustomerDetails() - "+responseText);
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getEmployeesDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		System.out.println("AjaxAction: Name is:" + name);
		String responseText = "";
		List<Employee> emp = new EmployeeDAO().getEmployeeDetails(name);
		Iterator<Employee> iter = emp.iterator();
		JSONObject list = new JSONObject();
		JSONArray empArray = new JSONArray();
		while (iter.hasNext()) {
			Employee e = iter.next();

			JSONObject empObj = new JSONObject();

			empObj.put("eid", e.getEmployee_id());
			empObj.put("doj", e.getDate_of_join().toString());
			empObj.put("salary", e.getSalary());
			empObj.put("role", e.getRole());

			JSONObject usr = new JSONObject();
			User user = e.getUser();
			usr.put("name", user.getFirstName() + " " + user.getLastName());
			usr.put("mobile", user.getMobile());
			usr.put("email", user.getEmail());

			JSONObject addr = new JSONObject();
			Address address = e.getAddress();
			addr.put("house_no", address.getHouseNo());
			addr.put("line1", address.getLine1());
			addr.put("line2", address.getLine2());
			addr.put("city", address.getCity());
			addr.put("state", address.getState());
			addr.put("zip", address.getZip());

			empObj.put("user", usr);
			empObj.put("address", addr);
			empArray.add(empObj);
		}

		list.put("EmpDetails", empArray);
		responseText = list.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getReturnDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int orderID = Integer.parseInt(request.getParameter("orderID"));
		JSONArray purchases = new ReturnDAO().getAllReturn(orderID);
		JSONObject resp = new JSONObject();
		if (purchases.size() > 0) {
			resp.put("status", "success");
		} else {
			resp.put("status", "failure");
		}
		resp.put("returns", purchases);
		String respJSON = resp.toJSONString();
		System.out.println(respJSON);
		response.setContentType("text/json");
		response.getWriter().write(respJSON);
	}

	public void getPurchaseData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		JSONArray purchases = new PurchaseDAO().getAllPurchasesJSON();
		JSONObject resp = new JSONObject();
		if (purchases.size() > 0) {
			resp.put("status", "success");
		} else {
			resp.put("status", "failure");
		}
		resp.put("purchases", purchases);
		String respJSON = resp.toJSONString();
		System.out.println(respJSON);
		response.setContentType("text/json");
		response.getWriter().write(respJSON);
	}

	public void getCities(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String state = request.getParameter("state");
		List<String> cities = new OrderDAO().getCity(state);
		JSONArray citiesJSON = new JSONArray();
		JSONObject resp = new JSONObject();

		for (String city : cities) {
			citiesJSON.add(city);
		}

		resp.put("city", citiesJSON);
		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getAttendanceDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String employee_id = request.getParameter("eid");

		System.out.println("AjaxAction called: Employee ID:" + employee_id);
		String responseText = "";
		boolean result = new AttendanceDAO().getAttendanceToday(employee_id);

		JSONObject obj = new JSONObject();
		obj.put("result", result);
		responseText = obj.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);

	}

	public void getComplaintByOIDCIDMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String oid = request.getParameter("OrderID");
		String cid = request.getParameter("CompID");
		String mob = request.getParameter("mobile");

		System.out.println("ORderID:" + oid + ",CompID:" + cid + ",Mobile:"
				+ mob);
		String responseText = "";

		List<Complaint> complaint = new ComplaintDAO().getComplaintDetails(oid,
				cid, mob);

	}

	public void getEmpDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		System.out.println("AjaxAction: Name is:" + name);
		String responseText = "";
		List<Employee> emp = new EmployeeDAO().getEmpDetails(name);
		Iterator<Employee> iter = emp.iterator();
		JSONObject list = new JSONObject();
		JSONArray empArray = new JSONArray();
		while (iter.hasNext()) {
			Employee e = iter.next();

			JSONObject empObj = new JSONObject();

			empObj.put("eid", e.getEmployee_id());
			empObj.put("doj", e.getDate_of_join().toString());
			empObj.put("salary", e.getSalary());

			JSONObject usr = new JSONObject();
			User user = e.getUser();
			usr.put("name", user.getFirstName() + " " + user.getLastName());
			usr.put("mobile", user.getMobile());
			usr.put("email", user.getEmail());

			empObj.put("user", usr);
			empArray.add(empObj);
		}

		list.put("EmpDetails", empArray);
		responseText = list.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
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

		Vector<String> emailVector = new Vector<>();
		Vector<String> mobileVector = new Vector<>();

		for (int i = 0; i < email.length; i++) {
			if (EmailValidator.getInstance().isValid(email[i]))
				emailVector.add(email[i]);
		}

		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i].matches(mobileReg))
				mobileVector.add(mobile[i]);
		}

		email = toStringArray(emailVector.toArray());
		mobile = toStringArray(mobileVector.toArray());

		Payment payment = (Payment) request.getSession()
				.getAttribute("payment");
		Merchant merchant = (Merchant) request.getSession().getAttribute(
				"merchant");

		Order order = (Order) request.getSession().getAttribute("order");
		String responseText = "";
		if (merchant == null) {
			responseText = "{ \"status\":\"failure\" }";
		} else if (email.length != 0) {
			new Email().sendReceiptDetails(email, payment, merchant);
			responseText = "{ \"status\":\"success\" }";
		}

		DBTSms.sendReceiptSMS(mobile, payment, order);
		System.out.println(responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
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
				paymentJSON.put("datetime", payment.getDatetime().toString());
				paymentArray.add(paymentJSON);
			}
			JSONObject resp = new JSONObject();
			resp.put("status", "success");
			resp.put("payments", paymentArray);
			resp.put("extraAmount", new PaymentDAO().getExtraAmount(order));
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

	public String[] toStringArray(Object[] arr) {
		String[] returnArray = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			returnArray[i] = arr[i].toString();
		}
		return returnArray;
	}

	private void sendShipmentDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String mobileReg = "^[7-9]{1}[0-9]{9}$";

		String emails = request.getParameter("email");
		String mobiles = request.getParameter("mobile");
		String[] email = emails.split(",");
		String[] mobile = mobiles.split(",");

		Vector<String> emailVector = new Vector<>();
		Vector<String> mobileVector = new Vector<>();

		for (int i = 0; i < email.length; i++) {
			if (EmailValidator.getInstance().isValid(email[i]))
				emailVector.add(email[i]);
		}

		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i].matches(mobileReg))
				mobileVector.add(mobile[i]);
		}

		email = toStringArray(emailVector.toArray());
		mobile = toStringArray(mobileVector.toArray());

		Shipment shipment = (Shipment) request.getSession().getAttribute(
				"shipment");
		int orderid = Integer.parseInt(request.getSession()
				.getAttribute("orderID").toString());
		Order order = new OrderDAO().getOrder(orderid);
		Merchant merchant = (Merchant) request.getSession().getAttribute(
				"merchant");
		String responseText = "";
		if (merchant == null) {
			responseText = "{ \"status\":\"failure\" }";
		} else if (email.length != 0) {
			new Email().sendShipment(email, shipment, order, merchant);
			responseText = "{ \"status\":\"success\" }";
		}

		DBTSms.sendShipmentSMS(mobile, shipment, order);
		response.setContentType("text/json");
		response.getWriter().write(responseText);

	}

	public void getOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String oid = request.getParameter("order");
		String mob = request.getParameter("mobile");
		String responseText = "";
		// System.out.println("AjaxActionServlet : "+name+oid+mob);

		List<Order> order = new OrderDAO().getOrderDetails(oid, name, mob);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray custArray = new JSONArray();
		while (iter.hasNext()) {
			Order o = iter.next();
			JSONObject ord = new JSONObject();
			ord.put("oid", o.getId());
			ord.put("amount", o.getAmount());
			ord.put("date", o.getDatetime().toString());

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
			Email.sendExceptionReport(e);
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

		List<Order> order = new ComplaintDAO().getOrderDetails(name, oid, mob);
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
		List<Customer> customers = new CustomerDAO().getCustomer(name);
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
		// logger.info("getCustomerDetails() - "+responseText);
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

		Vector<String> emailVector = new Vector<>();
		Vector<String> mobileVector = new Vector<>();

		for (int i = 0; i < email.length; i++) {
			if (EmailValidator.getInstance().isValid(email[i]))
				emailVector.add(email[i]);
		}

		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i].matches(mobileReg))
				mobileVector.add(mobile[i]);
		}

		email = toStringArray(emailVector.toArray());
		mobile = toStringArray(mobileVector.toArray());

		Order order = (Order) request.getSession().getAttribute("order");
		Order orderSend = new OrderDAO().getOrder(order.getId());
		Merchant merchant = (Merchant) request.getSession().getAttribute(
				"merchant");
		String resposneText = "";
		if (merchant == null) {
			resposneText = "{ \"status\":\"failure\" }";
		} else if (email.length != 0) {
			new Email().sendOrderDetails(email, orderSend, merchant);
			resposneText = "{ \"status\":\"success\" }";
		}

		DBTSms.sendOrderSMS(mobile, order);
		response.setContentType("text/json");
		response.getWriter().write(resposneText);

	}

	public void getProductsByCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String responseText = "";
		int catgId = Integer.parseInt(request.getParameter("catgId"));
		List<Product> products = new OrderDAO().getProducts(catgId);
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

	public void getExpenditureDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String responseString = "";
		// System.out.println("Inside getExpenditureDetail()");
		String type = request.getParameter("expType");
		JSONObject list = new JSONObject();
		JSONArray detailArray = new JSONArray();
		// System.out.println(type);

		if (type.equals("loan")) {
			List<Loan> loanDetails = new ExpenditureDAO().getLoanDetails();
			Iterator<Loan> itr = loanDetails.iterator();
			while (itr.hasNext()) {
				Loan l = itr.next();
				JSONObject loan = new JSONObject();
				loan.put("id", l.getId());
				loan.put("amount", l.getAmount());
				loan.put("installment", l.getInstallement());
				detailArray.add(loan);
				// System.out.println(l.getAmount() + ", " +
				// l.getInstallement());
			}
			list.put("expDetail", detailArray);
			list.put("type", "loan");
			responseString = list.toJSONString();

			System.out.println("AjaxAction : Response JSON is : "
					+ responseString);
			response.setContentType("text/json");
			response.getWriter().write(responseString);
		} else if (type.equals("interest")) {
			List<Capital> interestDetails = new ExpenditureDAO()
					.getInterestDetail();
			Iterator<Capital> itr = interestDetails.iterator();
			while (itr.hasNext()) {
				Capital c = itr.next();
				JSONObject interest = new JSONObject();
				interest.put("id", c.getId());
				interest.put("amount", c.getAmount());
				interest.put("lender", c.getLender());
				detailArray.add(interest);
				System.out.println(c.getAmount() + ", " + c.getLender());
			}
			list.put("expDetail", detailArray);
			list.put("type", "interest");
			responseString = list.toJSONString();

			System.out.println("AjaxAction : Response JSON is : "
					+ responseString);
			response.setContentType("text/json");
			response.getWriter().write(responseString);
		}
	}

	public void getEmployeeDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String responseText = "";
		List<User> employees = new EmployeeDAO().getEmployee();
		Iterator<User> iter = employees.iterator();
		JSONObject list = new JSONObject();
		JSONArray employeeArray = new JSONArray();

		while (iter.hasNext()) {
			User p = iter.next();
			JSONObject employee = new JSONObject();
			employee.put("empId", p.getId());
			employee.put("name", p.getFirstName() + " " + p.getLastName());
			employee.put("mobile", p.getMobile());

			employeeArray.add(employee);
		}
		list.put("employees", employeeArray);
		responseText = list.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String catName = request.getParameter("catName");
		System.out.println("At AjaxAction - addCategory(), catname : "
				+ catName);
		boolean status = new StockDAO().addNewCategory(catName);

		if (status)
			System.out.println("New Category added successfully");
		else
			System.out.println("Unable to add new category");

	}

	public void addNewProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int catId = Integer.parseInt(request.getParameter("catId"));
		String prodName = request.getParameter("prodName");
		int prodQty = Integer.parseInt(request.getParameter("prodQty"));
		int prodSp = Integer.parseInt(request.getParameter("prodSp"));
		int prodCp = Integer.parseInt(request.getParameter("prodCp"));

		System.out.println("At AjaxAction - addProduct(), Product name : "
				+ prodName + ", Category ID : " + catId);

		boolean status = new StockDAO().addNewProduct(catId, prodName, prodQty,
				prodSp, prodCp);

		if (status)
			System.out.println("New Product added successfully");
		else
			System.out.println("Unable to add new product");

	}

	public void addNewMerchant(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("merchantName");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String tin = request.getParameter("tin");
		String type = request.getParameter("type");

		System.out.println("At AjaxAction - addNewMerchant(), Merchant name : "
				+ name);

		boolean status = new MerchantDAO().addMerchant(name, mobile, email,
				tin, type);

		if (status)
			System.out.println("New Merchant added successfully");
		else
			System.out.println("Unable to add new merchant");
	}

}
