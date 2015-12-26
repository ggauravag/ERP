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

	public JSONObject getPurchaseById(int pid) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		JSONObject obj = new JSONObject();
		JSONArray products = new JSONArray();
		JSONArray payments = new JSONArray();
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select purchase._id,date,merchant_id,purchase.amount,product_id,product.name,purchase_item.quantity,purchase_item.amount as 'pamount',purchase_item.amount/purchase_item.quantity as 'rate',merchant_name,mobile,email,tin from purchase join purchase_item on purchase._id = purchase_item.purchase_id join product on purchase_item.product_id = product._id join merchant on merchant._id = purchase.merchant_id where purchase._id = ?;");
			stmt.setInt(1, pid);
			res = stmt.executeQuery();
			JSONObject product = new JSONObject();

			if (res.next()) {
				obj.put("id", res.getInt("_id"));
				obj.put("date", res.getDate("date").toString());
				obj.put("amount", res.getInt("amount"));
				obj.put("merchant", res.getString("merchant_name"));
				obj.put("mobile", res.getString("mobile"));
				obj.put("email", res.getString("email"));
				obj.put("tin", res.getString("tin"));

				product.put("name", res.getString("name"));
				product.put("quantity", res.getInt("quantity"));
				product.put("rate", (int) res.getDouble("rate"));
				product.put("amount", res.getInt("pamount"));
				products.add(product);
			}

			while (res.next()) {
				product = new JSONObject();
				product.put("name", res.getString("name"));
				product.put("quantity", res.getInt("quantity"));
				product.put("rate", (int) res.getDouble("rate"));
				product.put("amount", res.getInt("pamount"));
				products.add(product);
			}

			stmt.close();
			res.close();
			stmt = con
					.prepareStatement("select transaction_id,expenditure_id,`timestamp`,purchase_id,amount,`mode`,paid_by from purchase_expenditure join expenditure on purchase_expenditure.expenditure_id = expenditure._id join `transaction` on `transaction`._id = expenditure.transaction_id where `transaction`.amount != 0 and purchase_expenditure.purchase_id = ?  order by `timestamp`;");
			stmt.setInt(1, pid);
			res = stmt.executeQuery();
			while (res.next()) {
				JSONObject payment = new JSONObject();
				payment.put("id", res.getInt("transaction_id"));
				payment.put("date", res.getTimestamp("timestamp").toString());
				payment.put("amount", res.getInt("amount"));
				String[] desc = res.getString("mode").split(";");
				payment.put("mode", desc[0]);
				if (desc.length == 2) {
					payment.put("description", desc[1]);
				} else {
					payment.put("description", "NA");
				}
				payment.put("paidBy", res.getString("paid_by"));
				payments.add(payment);
			}

			obj.put("products", products);
			obj.put("payments", payments);
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return obj;
	}

	public boolean deletePurchase(int pid) {
		boolean result = true;
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("delete from purchase where _id = ?");
			stmt.setInt(1, pid);
			int i = stmt.executeUpdate();
			if (i == 0)
				result = false;
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return result;
	}

	public JSONArray getPurchaseDetail(String name, String fromdate,
			String todate, int pid) {
		JSONArray purchases = new JSONArray();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			if(!"".equals(name))
			{
				name = "%"+name+"%";
			}
			stmt = con
					.prepareStatement("select purchase._id,purchase.amount, purchase.date,merchant.merchant_name,merchant.mobile from purchase join merchant on merchant._id = purchase.merchant_id where merchant.merchant_name like '"
							+ name
							+ "' or (`date` >= ? and `date` <= ?) or purchase._id = ? order by purchase.date,purchase._id;");
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
			System.out.println("Num of record : " + stmt.toString());
			res = stmt.executeQuery();
			
			while (res.next()) {
				JSONObject purchase = new JSONObject();
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
