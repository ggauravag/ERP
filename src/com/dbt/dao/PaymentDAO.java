package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Payment;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class PaymentDAO {

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
		} catch (SQLException | NoConnectionException e) {
			e.printStackTrace();
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return payment;
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
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return payments;
	}

	public Payment makePayment(String paidBy, int amount, int orderID,
			String mode, String desc) {
		Payment pay = null;
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{Call CreatePayment(?,?,?,?)}");
			stmt.setInt(1, orderID);
			stmt.setInt(2, amount);
			stmt.setString(3, paidBy);
			stmt.setString(4, mode + ";" + desc);
			if (stmt.execute()) {
				res = stmt.getResultSet();
				if (res.next())
					pay = new Payment(res.getInt("transaction_id"),
							res.getString("mode"), res.getString("paid_by"),
							res.getTimestamp("timestamp"),
							res.getInt("amount"), res.getInt("order_id"),
							res.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return pay;
	}
}
