package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class ReturnDAO {

	public boolean processPayment(int orderID, int amount, String mode,
			String paidBy) {
		boolean result = false;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("insert into `transaction`(timestamp,amount,mode,paid_by) values(now(),?,?,?)");
			stmt.setInt(1, amount);
			stmt.setString(2, mode);
			stmt.setString(3, paidBy);
			int i = stmt.executeUpdate();
			if (i == 1) {
				stmt.close();
				stmt = con.prepareStatement("select last_insert_id();");
				res = stmt.executeQuery();
				int txnID = 0;
				if (res.next()) {
					txnID = res.getInt(1);
				}
				stmt.close();
				stmt = con
						.prepareStatement("insert into payment values(?,?,?)");
				stmt.setInt(1, txnID);
				stmt.setInt(2, orderID);
				stmt.setString(3, "DEBIT");
				i = stmt.executeUpdate();
				if(i != 0)
					result = true;
			}
		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(null, stmt, res);
		}
		return result;
	}

	public boolean returnProducts(int orderId, int[] prodID, int[] returnQty) {
		boolean success = true;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("update product,order_item set order_item.quantity = order_item.quantity - ?,product.quantity = product.quantity + ? where product._id = order_item.product_id and order_item.order_id = ? and order_item.product_id = ?");
			PreparedStatement insertStmt = con
					.prepareStatement("insert into `returns`(order_id,product_id,qty,datetime) values(?,?,?,now())");
			for (int i = 0; i < prodID.length; i++) {
				stmt.setInt(1, returnQty[i]);
				stmt.setInt(2, returnQty[i]);
				stmt.setInt(3, orderId);
				stmt.setInt(4, prodID[i]);
				stmt.addBatch();

				insertStmt.setInt(1, orderId);
				insertStmt.setInt(2, prodID[i]);
				insertStmt.setInt(3, returnQty[i]);
				insertStmt.addBatch();
			}

			int[] result = stmt.executeBatch();
			int[] resultInsert = insertStmt.executeBatch();

			for (int i = 0; i < prodID.length; i++) {
				if (result[i] != 1 || resultInsert[i] != 1) {
					success = false;
				}
			}

		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(null, stmt, null);
		}
		return success;
	}

	public JSONArray getAllReturn(int orderID) {
		JSONArray returns = new JSONArray();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select oe.order_id as 'order_id',oe.product_id,quantity,(select name from product where _id = oe.product_id) as name,amount,date(`datetime`) as return_time,qty as 'return_qty' from order_item oe left join `returns` r on oe.order_id = r.order_id and oe.product_id = r.product_id where oe.order_id = ?");
			stmt.setInt(1, orderID);
			set = stmt.executeQuery();
			while (set.next()) {
				JSONObject returnObj = new JSONObject();
				returnObj.put("orderID", set.getInt("order_id"));
				returnObj.put("productID", set.getInt("product_id"));
				returnObj.put("productName", set.getString("name"));
				returnObj.put("quantity", set.getInt("quantity"));
				returnObj.put("rate", set.getInt("amount"));
				if (set.getString("return_time") == null) {
					returnObj.put("isReturned", false);
				} else {
					returnObj.put("isReturned", true);
					returnObj.put("returnQuantity", set.getInt("return_qty"));
					returnObj.put("returnTime", set.getString("return_time"));
				}

				returns.add(returnObj);
			}
		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(null, stmt, set);
		}

		return returns;
	}

}
