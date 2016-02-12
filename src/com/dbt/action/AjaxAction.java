package com.dbt.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dbt.dao.AttendanceDAO;
import com.dbt.dao.ComplaintDAO;
import com.dbt.dao.CustomerDAO;
import com.dbt.dao.DocumentDAO;
import com.dbt.dao.EmployeeDAO;
import com.dbt.dao.ExpenditureDAO;
import com.dbt.dao.FeedbackDAO;
import com.dbt.dao.MerchantDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.dao.PaymentDAO;
import com.dbt.dao.ProductDAO;
import com.dbt.dao.PurchaseDAO;
import com.dbt.dao.ReturnDAO;
import com.dbt.dao.ShipmentDAO;
import com.dbt.dao.StockDAO;
import com.dbt.dao.TransactionDAO;
import com.dbt.dao.UtilityDAO;
import com.dbt.data.Address;
import com.dbt.data.Attendance;
import com.dbt.data.Capital;
import com.dbt.data.Complaint;
import com.dbt.data.Customer;
import com.dbt.data.Employee;
import com.dbt.data.Expenditure;
import com.dbt.data.Feedback;
import com.dbt.data.Loan;
import com.dbt.data.Merchant;
import com.dbt.data.Order;
import com.dbt.data.Order_item;
import com.dbt.data.Payment;
import com.dbt.data.Product;
import com.dbt.data.Question;
import com.dbt.data.Reminder;
import com.dbt.data.Transaction;
import com.dbt.data.User;
import com.dbt.support.DBTSms;
import com.dbt.support.Document;
import com.dbt.support.DropBoxConfig;
import com.dbt.support.Email;
import com.dbt.vo.Shipment;

public class AjaxAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		String action = "";
		// System.out.println("AjaxActionServlet: service(): action = " +
		// action);

		action = request.getParameter("action");
		
		if ("getCustomerDetails".equals(action)) {
			getCustomerDetails(request, response);
		}

		else if ("sendOrderDetails".equals(action)) {
			sendOrderDetails(request, response);
		}

		else if ("getProductByCategory".equals(action)) {
			getProductsByCategory(request, response);
		}

		else if ("getOrderByNameIDMobile".equals(action)) {
			getOrderByNameIDMobile(request, response);
		}

		else if ("getFirmsDetails".equals(action)) {
			getFirmsDetails(request, response);
		}

		else if ("sendShipmentDetails".equals(action)) {
			sendShipmentDetails(request, response);
		}

		else if ("getUserName".equals(action)) {
			getUserName(request, response);
		}

		else if ("setFirm".equals(action)) {
			setFirm(request, response);
		}
		else if ("getOrder".equals(action)) {
			getOrder(request, response);
		}

		else if ("checkStock".equals(action)) {
			checkStock(request, response);
		}

		else if ("getPaymentDetails".equals(action)) {
			getPaymentDetails(request, response);
		}

		else if ("sendReceiptDetails".equals(action)) {
			sendReceiptDetails(request, response);
		}

		else if ("getShipmentDetails".equals(action)) {
			getShipmentDetails(request, response);
		}

		else if ("getExpenditureDetail".equals(action)) {
			getExpenditureDetail(request, response);
		}

		else if ("getEmployeeDetails".equals(action)) {
			getEmployeeDetails(request, response);
		}

		else if ("addCategory".equals(action)) {
			addCategory(request, response);
		}

		else if ("addNewProduct".equals(action)) {
			addNewProduct(request, response);
		}

		else if ("addNewMerchant".equals(action)) {
			addNewMerchant(request, response);
		}
		else if ("getComplaintByOIDCIDMobile".equals(action)) {
			getComplaintByOIDCIDMobile(request, response);
		}
		else if ("getAttendanceDetails".equals(action)) {
			getAttendanceDetails(request, response);
		}
		else if ("getEmpDetails".equals(action)) {
			//System.out.println("Ajax called");
			getEmpDetails(request, response);
		}

		else if ("getCities".equals(action)) {
			getCities(request, response);
		}

		else if ("getPurchaseData".equals(action)) {
			getPurchaseData(request, response);
		}

		else if ("getReturnDetails".equals(action)) {
			getReturnDetails(request, response);
		}

		else if ("getEmployeesDetails".equals(action)) {
			getEmployeesDetails(request, response);
		}

		else if ("getCustomerByNameMob".equals(action)) {
			getCustomerByNameMob(request, response);
		}

		else if ("getPurchaseDetail".equals(action)) {
			getPurchaseDetail(request, response);
		}

		else if ("getPurchaseById".equals(action)) {
			getPurchaseById(request, response);
		}

		else if ("getProductsByOrderId".equals(action)) {
			getProductsByOrderId(request, response);
		}

		else if ("getCustomerDetailsById".equals(action)) {
			getCustomerDetailsById(request, response);
		}

		else if ("deleteOrderedProductById".equals(action)) {
			deleteOrderedProductById(request, response);
		}

		else if ("getFeedback".equals(action)) {
			getFeedback(request, response);
		}

		else if ("setReminder".equals(action)) {
			setReminder(request, response);
		}

		else if ("getReminder".equals(action)) {
			getReminder(request, response);
		}

		else if ("getEmployeeAttendance".equals(action)) {
			getEmployeeAttendance(request, response);
		}

		else if ("getTransaction".equals(action)) {
			getTransaction(request, response);
		}
		
		else if("getExpenditureById".equals(action)){
			getExpenditureById(request, response);
		}
		
		else if("getPaymentById".equals(action)){
			getPaymentById(request, response);
		}
		
		else if("getOrderAndPurchase".equals(action)){
			getOrderAndPurchase(request, response);
		}
		
		else if("getDocumentsById".equals(action)){
			getDocumentsById(request, response);
		}
		
		else if("removeDocument".equals(action)){
			removeDocument(request, response);
		}
		
		else if("addCityInState".equals(action)){
			addCityInState(request, response);
		}
		
		return null;
	}
	
	public void addCityInState(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		JSONObject responseJSON = new JSONObject();
		if(city == null || state == null || city.isEmpty() || state.isEmpty())
		{
			responseJSON.put("status", false);
			responseJSON.put("message", "City Name or State name is empty !");
		}
		else
		{
			OrderDAO dao = new OrderDAO();
			int result = dao.addCityInState(city, state);
			if(result == 0)
				responseJSON.put("status", true);
			else if(result == 1)
			{
				responseJSON.put("status", false);
				responseJSON.put("message", "City Already Exists In the State !");
			}
			else
			{
				responseJSON.put("status",false);
				responseJSON.put("message", "Something went unexpected ! Please try again !");
			}
				
		}
		String jsonResponse = responseJSON.toJSONString();
		System.out.println("AjaxActionServlet - addCityInStae() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}
	
	public void removeDocument(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("fileName");
		String inputId = request.getParameter("id");
		JSONObject responseJSON = new JSONObject();
		if(NumberUtils.isNumber(inputId) && !fileName.isEmpty())
		{
			int id = Integer.parseInt(inputId);
			DropBoxConfig config = new DropBoxConfig();
			DocumentDAO dao = new DocumentDAO();
			if(dao.removeDocument(id) && config.deleteFromDropBox(fileName))
			{
				responseJSON.put("status", 1);
			}
			else
			{
				responseJSON.put("status", 0);
			}
		}
		else
		{
			responseJSON.put("status", 2);
		}
		
		String jsonResponse = responseJSON.toJSONString();
		System.out.println("AjaxActionServlet - removeDocument() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}
	
	
	
	public void getDocumentsById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String inputId = request.getParameter("id");
		String inputType = request.getParameter("type");
		JSONObject responseJSON = new JSONObject();
		if(NumberUtils.isNumber(inputId))
		{
			JSONArray docs = new JSONArray();
			responseJSON.put("status", 1);
			Iterator<Document> iter = new DocumentDAO().getDocuments(Integer.parseInt(inputId), inputType).iterator();
			
			while(iter.hasNext())
			{
				Document document = iter.next();
				JSONObject doc = new JSONObject();
				doc.put("id", document.getId());
				doc.put("name", document.getName());
				doc.put("type", document.getType());
				doc.put("entityId", document.getEntityId());
				doc.put("url", document.getUrl());
				docs.add(doc);
			}
			
			responseJSON.put("documents", docs);
		}
		else
		{
			responseJSON.put("status", 0);
		}
		
		String jsonResponse = responseJSON.toJSONString();
		System.out.println("AjaxActionServlet - getDocumentsById() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}
	
	public void getOrderAndPurchase(HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		String inputId = request.getParameter("inputId");
		String inputName = request.getParameter("inputName");
		String inputFromDate = request.getParameter("inputFromDate");
		String inputToDate = request.getParameter("inputToDate");
		String inputMobile = request.getParameter("inputMobile");
		int id;
		try
		{
			id = Integer.parseInt(inputId);
		}
		catch(Exception e)
		{
			id = 0;
		}
		
		Date startDate = null,endDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(!inputFromDate.equals("") && !inputToDate.equals(""))
		{
			startDate = format.parse(inputFromDate);
			endDate = format.parse(inputToDate);
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			endDate = calendar.getTime();
		}
		
		List<JSONObject> orders = new OrderDAO().getOrderAndPurchaseDetail(id, inputName, startDate, endDate, inputMobile);
		JSONObject resp = new JSONObject();
		resp.put("status", 1);
		resp.put("orders", orders);
		String jsonResponse = resp.toJSONString();
		System.out.println("AjaxActionServlet - getOrderAndPurchaseDetail() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}
	
	public void getExpenditureById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String expID = request.getParameter("relatedId");
		JSONObject resp = new JSONObject();
		if(NumberUtils.isNumber(id) && NumberUtils.isNumber(expID))
		{
			int transactId = Integer.parseInt(id);
			int expenditureId = Integer.parseInt(expID);
			Expenditure expenditure = new ExpenditureDAO().getExpenditureById(transactId, expenditureId);
			resp.put("id",expenditure.getId());
			resp.put("status", true);
			resp.put("type", expenditure.getType());
			resp.put("detail", expenditure.getDetails());
			JSONObject transaction = new JSONObject();
			Transaction tran = expenditure.getTransaction();
			transaction.put("id", tran.getTransactionId());
			transaction.put("amount", tran.getAmount());
			transaction.put("paidBy", tran.getPaidBy());
			transaction.put("mode", tran.getMode());
			transaction.put("description", tran.getDescription());
			transaction.put("type", tran.getType());
			transaction.put("relatedId", tran.getRelatedId());
			transaction.put("time", tran.getPrintableTime());
			resp.put("transaction", transaction);
		}
		
		String jsonResponse = resp.toJSONString();
		System.out.println("AjaxActionServlet - getExpenditureById() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
		
	}
	
	public void getPaymentById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String expID = request.getParameter("relatedId");
		JSONObject resp = new JSONObject();
		if(NumberUtils.isNumber(id) && NumberUtils.isNumber(expID))
		{
			int transactId = Integer.parseInt(id);
			int orderId = Integer.parseInt(expID);
			Payment payment = new PaymentDAO().getPaymentByID(transactId);
			
			resp.put("id", payment.getId());
			resp.put("amount", payment.getAmount());
			resp.put("paidBy", payment.getPaidBy());
			resp.put("mode", payment.getMode());
			resp.put("description", payment.getDescription());
			resp.put("type", payment.getType());
			resp.put("time", payment.getPrintableTime());

			Order order = new OrderDAO().getOrder(orderId);
			JSONObject orderResp = new JSONObject();
			orderResp.put("custName", order.getCustomer().getName());
			orderResp.put("amount",order.getAmount());
			orderResp.put("time", order.getPrintableTime());
			orderResp.put("id", order.getId());
			resp.put("order", orderResp);
		}
		resp.put("status", true);
		String jsonResponse = resp.toJSONString();
		System.out.println("AjaxActionServlet - getTransaction() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}

	public void getTransaction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String mobile = request.getParameter("mobile");
		String from = request.getParameter("from");
		String to = request.getParameter("end");
		String name = request.getParameter("name");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int transactId = 0;
		Date startDate = null,endDate = null;
		
		JSONObject res = new JSONObject();
		JSONArray transArray = new JSONArray();
		
		if(NumberUtils.isNumber(id))
			transactId = Integer.parseInt(id);
		
		if(!from.equals("") && !to.equals(""))
		{
			startDate = format.parse(from);
			endDate = format.parse(to);
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			endDate = calendar.getTime();
		}
		
		TransactionDAO dao = new TransactionDAO();
		List<Transaction> transactions = dao.getTransaction(transactId, name, mobile, startDate, endDate);
		Iterator<Transaction> transIter = transactions.iterator();
		JSONObject transaction;
		while(transIter.hasNext())
		{
			Transaction tran = transIter.next();
			transaction = new JSONObject();
			transaction.put("id", tran.getTransactionId());
			transaction.put("amount", tran.getAmount());
			transaction.put("paidBy", tran.getPaidBy());
			transaction.put("mode", tran.getMode());
			transaction.put("description", tran.getDescription());
			transaction.put("type", tran.getType());
			transaction.put("relatedId", tran.getRelatedId());
			transaction.put("time", tran.getPrintableTime());
			transArray.add(transaction);
		}
		
		res.put("status", true);
		res.put("transactions", transArray);
		String jsonResponse = res.toJSONString();
		System.out.println("AjaxActionServlet - getTransaction() : "+jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();
	}

	public void getEmployeeAttendance(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String empId = request.getParameter("empId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date fromDate = format.parse(startDate);
		Date toDate = format.parse(endDate);

		List<Attendance> attendance = new AttendanceDAO().getAttendance(
				new java.sql.Date(fromDate.getTime()),
				new java.sql.Date(toDate.getTime()), Integer.parseInt(empId));

		JSONObject resp = new JSONObject();
		JSONArray attJSON = new JSONArray();
		Iterator<Attendance> iter = attendance.iterator();

		while (iter.hasNext()) {
			Attendance att = iter.next();
			JSONObject tempJSON = new JSONObject();
			tempJSON.put("presentDate", att.getDate().toString());
			tempJSON.put("halfDay", att.getHalfDay());
			attJSON.add(tempJSON);
		}
		resp.put("attendance", attJSON);
		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void getReminder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Reminder> reminders = new UtilityDAO().getAllReminders();

		Iterator<Reminder> iterator = reminders.iterator();
		JSONObject resp = new JSONObject();
		JSONArray remindersJSON = new JSONArray();
		while (iterator.hasNext()) {
			Reminder reminder = iterator.next();
			JSONObject reminderJSON = new JSONObject();
			reminderJSON.put("title", reminder.getTitle());
			reminderJSON.put("message", reminder.getMessage());
			reminderJSON.put("startDate", reminder.getStartDate().toString());
			reminderJSON.put("endDate", reminder.getEndDate().toString());
			reminderJSON.put("frequency", reminder.getFrequency());
			remindersJSON.add(reminderJSON);
		}
		resp.put("status", true);
		resp.put("reminders", remindersJSON);
		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);

	}

	public void setReminder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String message = request.getParameter("message");
		String freq = request.getParameter("frequency");
		String title = request.getParameter("title");
		String mobiles = request.getParameter("mobile");
		String startDate = request.getParameter("from");
		String endDate = request.getParameter("to");

		String messageSend = "ALERT : " + title + "\n" + message;

		Date fromDate, toDate = null;

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
		System.out.println("Start : " + startDate + ", End : " + endDate);
		JSONObject resp = new JSONObject();
		try {
			fromDate = format.parse(startDate);
			toDate = format.parse(endDate);

			for (Date d = fromDate; d.compareTo(toDate) <= 0;) {
				DBTSms.schedule(mobiles, messageSend, d);
				System.out.println("Time is : " + d.toString());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				calendar.add(getUnit(freq), 1);
				// System.out.println("Freq is : "+freq+", Unit : "+getUnit(freq));
				d = calendar.getTime();
			}

			new UtilityDAO()
					.addReminder(message, freq, fromDate, toDate, title);
			resp.put("status", true);
		} catch (Exception e) {
			resp.put("status", false);
			resp.put("message", "Unable to parse Dates ! Can't set Reminder !");
		}

		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public int getUnit(String freq) {
		int result = 0;
		switch (freq) {
		case "DAILY":
			result = Calendar.DATE;
			break;
		case "MONTHLY":
			result = Calendar.MONTH;
			break;
		case "YEARLY":
			result = Calendar.YEAR;
			break;
		case "WEEKLY":
			result = Calendar.WEEK_OF_YEAR;
		}
		return result;
	}

	public void getFeedback(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String order = request.getParameter("orderId");
		String name = request.getParameter("custName");
		String mobile = request.getParameter("custMobile");
		String from = request.getParameter("fromdate");
		String to = request.getParameter("todate");

		int orderID = 0;
		Date toDate, fromDate;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if (!"".equals(order) && order != null) {
			orderID = Integer.parseInt(order);
		}

		try {
			fromDate = format.parse(from);
			toDate = format.parse(to);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// Email.sendExceptionReport(e);
			fromDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fromDate);
			calendar.add(Calendar.DATE, 1);
			fromDate = calendar.getTime();

			toDate = new Date();
			calendar = Calendar.getInstance();
			calendar.setTime(toDate);
			calendar.add(Calendar.DATE, 1);
			toDate = calendar.getTime();
		}

		List<Feedback> feedbacks = new FeedbackDAO().searchFeedback(orderID,
				name, mobile, toDate, fromDate);

		Iterator<Feedback> iterator = feedbacks.iterator();
		JSONObject resp = new JSONObject();
		JSONArray feedbackJSON = new JSONArray();

		while (iterator.hasNext()) {
			Feedback fbk = iterator.next();
			JSONObject fb = new JSONObject();
			fb.put("id", fbk.getId());
			fb.put("orderID", fbk.getOrderId());
			fb.put("generateDate", fbk.getGenerateDate().toString());
			if (fbk.getSubmitDate() != null)
				fb.put("submitDate", fbk.getSubmitDate().toString());
			else
				fb.put("submitDate", "");
			JSONArray questions = new JSONArray();
			Iterator<Question> ques = fbk.getQuestions().iterator();
			while (ques.hasNext()) {
				Question q = ques.next();
				JSONObject jsq = new JSONObject();
				jsq.put("id", q.getId());
				jsq.put("response", q.getResponse());
				jsq.put("text", q.getText());
				jsq.put("type", q.getType());
				questions.add(jsq);
			}
			fb.put("questions", questions);

			feedbackJSON.add(fb);
		}

		resp.put("status", true);
		resp.put("feedbacks", feedbackJSON);
		String responseText = resp.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);

	}

	public void getProductsByOrderId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int oid = Integer.parseInt(request.getParameter("orderId"));

		String responseText = "";
		System.out.println("AjaxActionServlet, Order Id :" + oid);

		List<Order> order = new OrderDAO().getProductsByOrderId(oid);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray orderArray = new JSONArray();

		while (iter.hasNext()) {
			Order o = iter.next();
			JSONObject ord = new JSONObject();

			JSONObject custom = new JSONObject();
			Customer cus = o.getCustomer();
			custom.put("customerName", cus.getName());
			custom.put("mobile", cus.getMobile());
			custom.put("email", cus.getEmail());
			custom.put("customerId", cus.getId());

			ord.put("customer", custom);

			JSONObject address = new JSONObject();
			Address add = cus.getAddress();
			address.put("house_no", add.getHouseNo());
			address.put("line1", add.getLine1());
			address.put("line2", add.getLine2());
			address.put("city", add.getCity());
			address.put("state", add.getState());
			address.put("zip", add.getZip());

			ord.put("address", address);

			JSONObject product = new JSONObject();
			Product p = o.getProduct();
			product.put("productName", p.getName());
			product.put("categoryName", p.getCategoryName());
			product.put("productId", p.getId());

			ord.put("product", product);

			JSONObject orderitem = new JSONObject();
			Order_item oi = o.getOrderitem();
			orderitem.put("orderId", oi.getOrder_id());
			orderitem.put("quantity", oi.getQuantity());
			orderitem.put("amount", oi.getAmount());
			orderitem.put("shipId", oi.getShip_id());

			ord.put("orderitem", orderitem);

			orderArray.add(ord);
		}
		list.put("orderdetails", orderArray);
		responseText = list.toJSONString();
		System.out.println("AjaxActionServlet : Response JSON is : "
				+ responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
	}

	public void deleteOrderedProductById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int prodId = Integer.parseInt(request.getParameter("productId"));
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int amount = Integer.parseInt(request.getParameter("prodAmount"));
		int quantity = Integer.parseInt(request.getParameter("prodQuantity"));

		System.out.println("At AjaxAction - deleteProduct(), Product Id : "
				+ prodId);

		boolean status = new OrderDAO().deleteProductById(prodId, orderId,
				amount, quantity);

		if (status)
			System.out.println("Product deleted successfully, Id : " + prodId);
		else
			System.out.println("Unable to delete product, Id : " + prodId);

	}

	public void getPurchaseById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String pid = request.getParameter("pid");
		int purchaseID = 0;
		if (pid != null && pid.length() != 0) {
			purchaseID = Integer.parseInt(pid);
		}

		JSONObject responseJSON = new PurchaseDAO().getPurchaseById(purchaseID);
		String responseText = responseJSON.toJSONString();
		System.out.println(responseText);
		response.setContentType("text/json");
		response.getWriter().write(responseText);
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
			if (p.getEmail() == null)
				c.put("email", "");
			else
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

	public void getCustomerDetailsById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int custId = Integer.parseInt(request.getParameter("customerId"));
		String responseText = "";
		List<Customer> customers = new CustomerDAO().getCustomerById(custId);
		Iterator<Customer> iter = customers.iterator();
		JSONObject list = new JSONObject();
		JSONArray productsArray = new JSONArray();
		while (iter.hasNext()) {
			Customer p = iter.next();
			JSONObject product = new JSONObject();
			product.put("id", p.getId());
			product.put("name", p.getName());
			if (p.getEmail() == null)
				product.put("email", "");
			else
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
		String responseText = "{ \"status\":\"success\" }";
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
			PaymentDAO paymentDAO = new PaymentDAO();
			resp.put("discountAmount", paymentDAO.getDiscount(order));
			resp.put("shippingCharge", paymentDAO.getShippingCharge(order));
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
		String responseText = "{ \"status\":\"success\" }";
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
		String toDate = request.getParameter("to");
		String fromDate = request.getParameter("from");
		Date to = null, from = null;
		if (!"".equals(toDate) && !"".equals(fromDate)) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				to = format.parse(toDate);
				from = format.parse(fromDate);
			} catch (Exception e) {
				to = new Date();
				from = new Date();
			}
		} else {
			to = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(to);
			calendar.add(Calendar.DATE, 1);
			to = calendar.getTime();

			from = new Date();
			calendar.setTime(from);
			calendar.add(Calendar.DATE, 1);
			from = calendar.getTime();
		}
		String responseText = "";
		// System.out.println("AjaxActionServlet : "+name+oid+mob);

		List<Order> order = new OrderDAO().getOrderDetails(oid, name, mob, to,
				from);
		Iterator<Order> iter = order.iterator();
		JSONObject list = new JSONObject();
		JSONArray custArray = new JSONArray();
		while (iter.hasNext()) {
			Order o = iter.next();
			JSONObject ord = new JSONObject();
			ord.put("oid", o.getId());
			ord.put("amount", o.getAmount());
			ord.put("date", o.getDatetime().toString());
			ord.put("status", o.getStatus());
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

		// System.out.println(firm + " || " + fid);
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
			if (p.getEmail() == null)
				product.put("email", "");
			else
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
		String resposneText = "{ \"status\":\"success\" }";
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
