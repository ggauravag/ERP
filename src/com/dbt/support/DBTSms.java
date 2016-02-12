package com.dbt.support;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.text.WordUtils;

import com.dbt.data.Order;
import com.dbt.data.Payment;
import com.dbt.database.DBConnection;
import com.dbt.vo.Shipment;

public class DBTSms {

	public static String retval = "";

	/**
	 * @param args
	 */

	public static String schedule(String msidn, String msg, Date date) {
		String rsp = "";
		// String user = AESCrypto.decrypt("7fMs2hJHbPeLdaQM93dBJA==");

		System.out.println("SMS Scheduled ! ");
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyykk:mm");
			System.out.println("Scheduled time is : "+format.format(date));
			URL url = new URL(
					"http://198.24.149.4/API/pushsms.aspx?loginID=ramfur&password=programmer&mobile="
							+ URLEncoder.encode(msidn, "UTF-8")
							+ "&text="
							+ URLEncoder.encode(msg,"UTF-8")
							+ "&senderid=RAMFUR&route_id=2&Unicode=0&sch="
							+ URLEncoder.encode(format.format(date),"UTF-8") + "");
			
			URLConnection conn = url.openConnection();
			conn.setDoOutput(false);

		
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				retval += line;
			}
			// wr.close();
			rd.close();

			System.out.println(retval);
			rsp = retval;

		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}
		return rsp;
	}

	public static String sendSMSTeneti(String sNumber, String message) {
		try {
			String sUserID = "ishashank22";
			String sPwd = "ishashank22";
			String code = "TP";
			String sSID = "DBTECH";
			String sMessage = message;
			String sURL = "http://sms.dreambit.in/SendSMS.aspx?username="
					+ sUserID + "&";
			sURL += "password=" + sPwd + "&mobile=" + sNumber + "&sid=" + sSID
					+ "&code=" + code + "&msg=" + URLEncoder.encode(sMessage);

			URL url = new URL(sURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(false);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				retval += line;
			}
			// wr.close();
			rd.close();

			System.out.println(retval);
			return retval;
		} catch (Exception e) {
			Email.sendExceptionReport(e);
			return "";
		}

		// 103.247.98.91/API/SendMsg.aspx?uname=xxxxxxxx&pass=xxxxx&send=xxxxxx&dest=xxxxxxxxxx&msg=xxxxxxx&priority=1&schtm=xxxx-xx-xx
		// xx:xx
	}

	public static String sendFeedback(String mobile, String item, String name,
			String token, boolean shopclues) {
		String resp;
		String msg;
		if (shopclues) {
			msg = "Dear "
					+ name
					+ ",\nKindly give us feedback for the order("
					+ item
					+ ") that you had placed on ShopClues. Click here http://erp.dreambit.co.in/feedback/feedback.jsp?token="
					+ token + "";
		} else {
			msg = "Dear "
					+ name
					+ ",\nKindly give us feedback for the order("
					+ item
					+ ") you had placed with us. Click here http://erp.dreambit.co.in/feedback/feedback.jsp?token="
					+ token + "";
		}
		resp = sendSMS(mobile, msg);
		return resp;
	}

	public static String sendOwnerOTP(String mobile, String name) {
		String otp = Utils.getOTP(6);
		String msg = name.toUpperCase()
				+ " is trying to access ERP Portal, if he/she is authorised then pass this OTP : "
				+ otp + " to him/her.";
		sendSMS(mobile, msg);
		return otp;
	}

	public static void sendOrderSMS(String[] mobile, Order order) {
		String text = "Dear " + order.getCustomer().getName()
				+ ",\nYour order has been placed with Order ID : "
				+ order.getId() + ", and amount of order is : "
				+ order.getAmount()
				+ ". Please refer the order ID for future purposes.";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i] != null && i != mobile.length - 1)
				buffer.append(mobile[i] + ",");
			else if (mobile[i] != null)
				buffer.append(mobile[i]);
		}
		sendSMS(buffer.toString(), text);
	}

	public static void sendReceiptSMS(String[] mobile, Payment payment,
			Order order) {
		String text = "Dear " + order.getCustomer().getName()
				+ ",\nWe have received an amount of Rs. " + payment.getAmount()
				+ " for Order ID " + order.getId() + " as " + payment.getMode();
		if (!"".equals(payment.getDescription())) {
			text += "(" + payment.getDescription() + ")";
		}

		text += " from " + payment.getPaidBy() + " wide Txn ID "
				+ payment.getId() + ".";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i] != null && i != mobile.length - 1)
				buffer.append(mobile[i] + ",");
			else if (mobile[i] != null)
				buffer.append(mobile[i]);
		}
		sendSMS(buffer.toString(), text);
	}

	public static void sendShipmentSMS(String[] mobile, Shipment shipment,
			Order order) {
		String text = "Dear " + order.getCustomer().getName()
				+ ",\nYour order has been shipped by " + shipment.getMedium()
				+ ", No.(" + shipment.getMediumNumber() + "). Contact : "
				+ shipment.getMediumName() + " ( " + shipment.getContact()
				+ " ) for further tracking.";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < mobile.length; i++) {
			if (mobile[i] != null && i != mobile.length - 1)
				buffer.append(mobile[i] + ",");
			else if (mobile[i] != null)
				buffer.append(mobile[i]);
		}
		sendSMS(buffer.toString(), text);
	}

	public static String sendSMS(String msisdn, String msg) {
		String rsp = "";
		// String user = AESCrypto.decrypt("7fMs2hJHbPeLdaQM93dBJA==");
		String password = "programmer";
		String sid = "RAMFUR";
		// String flash = "0";
		// msisdn = "91"+msisdn;
		System.out.println("India SMS called");
		try {
			// Construct The 11Post Data

			// SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			// SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:00");
			java.util.Date date = new java.util.Date();
			// String sendondate =
			// format1.format(date)+"T"+format2.format(date);
			//

			String data = "&route_id=2&Unicode=0";
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode(password, "UTF-8");
			data += "&" + URLEncoder.encode("mobile", "UTF-8") + "="
					+ URLEncoder.encode(msisdn, "UTF-8");
			data += "&" + URLEncoder.encode("text", "UTF-8") + "="
					+ URLEncoder.encode(msg, "UTF-8");
			data += "&" + URLEncoder.encode("senderid", "UTF-8") + "="
					+ URLEncoder.encode(sid, "UTF-8");
			// data += "&" + URLEncoder.encode("sendondate", "UTF-8") + "=" +
			// "30-10-2014T23:58:00";

			// System.out.println("Data is : "+data);
			// Push the HTTP Request
			URL url = new URL(
					"http://198.24.149.4/API/pushsms.aspx?loginID=ramfur"
							+ data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(false);

			// OutputStreamWriter wr = new
			// OutputStreamWriter(conn.getOutputStream());
			// wr.write(data);
			// wr.flush();

			// Read The Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				retval += line;
			}
			// wr.close();
			rd.close();

			System.out.println(retval);
			rsp = retval;

		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}
		return rsp;
	}

	public static void main(String[] args) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps =
			 con.prepareStatement("SELECT DISTINCT mobile,CONCAT(first_name,' ',last_name) as 'name',a.city,a.state FROM `user` u JOIN address a ON a.user_id = u._id WHERE u.mobile <> '9999999999' AND a.state <> 'Rajasthan';");

			ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 String msg = "Dear "+WordUtils.capitalizeFully(rs.getString("name"))+",\nWe were privileged to serve you last year with our product & we wish your continued engagement with us this year too.\nWishing you and your family a very Happy New Year 2016.\nFind furniture products online with us.\nhttp://ram-furnitures-online.shopclues.com \nhttp://www.snapdeal.com/seller/ram-furniture/S83480 \nWe are on FlipKart & AskMe too.\nContact for Queries:\nGaurav 998216638\nRam Furniture";
				 sendSMS(rs.getString("mobile"), msg);
				 System.out.println("Name : "+WordUtils.capitalizeFully(rs.getString("name")+", Mobile : "+rs.getString("mobile")));
			 }
			 DBConnection.closeResource(con, ps, rs);
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}
		

	}
}
