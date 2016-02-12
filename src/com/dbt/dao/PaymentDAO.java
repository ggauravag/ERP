package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbt.data.Payment;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class PaymentDAO {

	public int getPayableReturnAmount(int orderID, int returnAmount) {
		int payableAmount = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select order.amount as 'amount',sum(transaction.amount) as 'paidamount' from `order`,payment,transaction where order._id = ? and order._id = payment.order_id and payment.transaction_id = transaction._id group by payment.order_id");
			stmt.setInt(1, orderID);
			set = stmt.executeQuery();
			if (set.next()) {
				int totalAmount = set.getInt("amount");
				int paidAmount = set.getInt("paidamount");
				if ((totalAmount - paidAmount) >= returnAmount) {
					payableAmount = 0;
				} else {
					payableAmount = returnAmount - (totalAmount - paidAmount);
				}

				stmt.close();
				stmt = con
						.prepareStatement("update order set amount = amount - ? where _id = ?");
				stmt.setInt(1, returnAmount);
				stmt.setInt(2, orderID);
				stmt.executeUpdate();
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return payableAmount;
	}
	
	public void getPaymentOrder(int transactID)
	{
		
	}

	public Payment getPaymentByID(int payID) {
		Payment payment = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select _id,`timestamp`,amount,mode,paid_by,transaction_id,order_id,type from transaction join payment on payment.transaction_id = transaction._id where _id = ?");
			stmt.setInt(1, payID);
			set = stmt.executeQuery();
			if (set.next()) {
				payment = new Payment(set.getInt("_id"), set.getString("mode"),
						set.getString("paid_by"),
						set.getTimestamp("timestamp"), set.getInt("amount"),
						set.getInt("order_id"), set.getString("type"));
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return payment;
	}
	
	public int getDiscount(int orderId)
	{
		int discount = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("SELECT discount FROM `order` WHERE _id = ?");
			stmt.setInt(1, orderId);
			set = stmt.executeQuery();
			if(set.next())
			{
				if(set.getString("discount") != null)
					discount = set.getInt("discount");
			}
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, set);
		}
		return discount;
	}
	
	public int getShippingCharge(int orderId)
	{
		int charge = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("SELECT distinct S._id, S.charge FROM `order_item` OI JOIN shipment S ON OI.ship_id = S._id WHERE OI.order_id = ?");
			stmt.setInt(1, orderId);
			set = stmt.executeQuery();
			while(set.next())
			{
				charge += set.getInt("charge");
			}
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, set);
		}
		return charge;
	}
	
	public int getExtraAmount(int orderID) {
		int extraAmount = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select discount,(select sum(distinct charge) from shipment,order_item where shipment._id = order_item.ship_id and order_item.order_id = `order`._id) as charge from `order` where _id = ?");
			stmt.setInt(1, orderID);
			set = stmt.executeQuery();
			if (set.next()) {
				int discount = set.getInt("discount");
				int charge = set.getInt("charge");
				extraAmount = charge - discount;
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return extraAmount;
	}

	public List<Payment> getPayment(int orderid) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Payment> payments = new ArrayList<Payment>();
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select _id,timestamp,amount,mode,paid_by,order_id,type from transaction join payment on transaction._id = payment.transaction_id where payment.order_id = ?");
			stmt.setInt(1, orderid);

			res = stmt.executeQuery();
			while (res.next()) {
				System.out.println("Type is : " + res.getString("type"));
				Payment payment = new Payment(res.getInt("_id"),
						res.getString("mode"), res.getString("paid_by"),
						res.getTimestamp("timestamp"), res.getInt("amount"),
						res.getInt("order_id"), res.getString("type"));
				payments.add(payment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return payments;
	}

	public Payment makePayment(String paidBy, int amount, int orderID,
			String mode, String desc, Date payDate, int discountAmount) {
		Payment pay = null;
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{Call CreatePayment(?,?,?,?,?)}");
			stmt.setInt(1, orderID);
			stmt.setInt(2, amount);
			stmt.setString(3, paidBy);
			stmt.setString(4, mode + ";" + desc);
			stmt.setTimestamp(5, new Timestamp(payDate.getTime()));
			if (stmt.execute()) {
				res = stmt.getResultSet();
				if (res.next())
					pay = new Payment(res.getInt("transaction_id"),
							res.getString("mode"), res.getString("paid_by"),
							res.getTimestamp("timestamp"),
							res.getInt("amount"), res.getInt("order_id"),
							res.getString("type"));
				if(discountAmount != 0)
					new OrderDAO().addUpDiscount(orderID, discountAmount);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return pay;
	}
}
