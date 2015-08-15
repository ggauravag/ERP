package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class PurchaseDAO {

	public JSONArray getPurchaseDetail(String name, String fromdate,
			String todate, int pid) {
		JSONArray purchases = new JSONArray();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select purchase._id,purchase.amount, purchase.date,merchant.merchant_name,merchant.mobile from purchase join merchant on merchant._id = purchase.merchant_id where merchant.merchant_name like '%"
							+ name
							+ "%' or (`date` >= ? and `date` <= ?) or purchase._id = ?;");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date toDate = null, fromDate = null;
			try {
				toDate = format.parse(todate);
				fromDate = format.parse(fromdate);
			} catch (Exception e) {
				toDate = new Date();
				fromDate = new Date();
			}
			stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
			stmt.setDate(2, new java.sql.Date(toDate.getTime()));
			stmt.setInt(3, pid);
			System.out.println("Num of record : "+stmt.toString());
			res = stmt.executeQuery();
			JSONObject purchase = new JSONObject();
			while (res.next()) {
				purchase.put("id", res.getInt("_id"));
				purchase.put("amount", res.getInt("amount"));
				purchase.put("date", res.getDate("date").toString());
				purchase.put("merchant", res.getString("merchant_name"));
				purchase.put("mobile", res.getString("mobile"));
				purchases.add(purchase);
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return purchases;
	}

	public JSONArray getAllPurchasesJSON() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		JSONArray purchase = new JSONArray();

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select merchant_name,p._id,p.date,p.amount,sum(t.amount) as paid from purchase p,merchant m,purchase_expenditure pe,expenditure e, `transaction` t where p.merchant_id = m._id and pe.expenditure_id = e._id and t._id = e.transaction_id and p._id = pe.purchase_id group by p._id;");
			res = stmt.executeQuery();
			while (res.next()) {
				JSONObject p = new JSONObject();
				p.put("id", res.getInt("_id"));
				p.put("date", res.getString("date"));
				p.put("amount", res.getInt("amount"));
				p.put("paid", res.getInt("paid"));
				p.put("merchant", res.getString("merchant_name"));
				purchase.add(p);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return purchase;
	}
}
