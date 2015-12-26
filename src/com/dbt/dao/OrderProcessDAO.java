package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;
import com.dbt.vo.Shipment;

public class OrderProcessDAO {

	public Shipment shipProducts(JSONArray itemJSON, int orderID, int[] prodID,
			String type, String name, String number, String contact,
			String datetime,int charge) {
		Shipment shipment = addShipment(type, name, number, contact, datetime,charge);

		Connection con = null;
		PreparedStatement stmt = null;
		List<Product> items = new ArrayList<Product>();

		try {
			con = DBConnection.getConnection();
			StringBuffer cond = new StringBuffer("");
			for (int k = 0; k < prodID.length; k++) {
				if (k != prodID.length - 1)
					cond.append(prodID[k] + ",");
				else
					cond.append(prodID[k]);
				JSONObject obj = (JSONObject) itemJSON.get(k);
				Product p = new Product(Integer.parseInt(obj.get("product_id")
						.toString()), 0, obj.get("product_name").toString(),
						Integer.parseInt(obj.get("product_qty").toString()),
						-1, -1);
				p.setOrderID(Integer.parseInt(obj.get("order_id").toString()));
				items.add(p);
			}
			shipment.setItems(items);
			String sql = "update product,order_item set product.quantity = product.quantity - order_item.quantity, order_item.ship_id = ? where product._id = order_item.product_id and order_item.order_id = ? and order_item.product_id in ("
					+ cond.toString() + ")";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, shipment.getId());
			stmt.setInt(2, orderID);
			int i = stmt.executeUpdate();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return shipment;
	}

	public Shipment addShipment(String type, String name, String number,
			String contact, String datetime,int charges) {
		int shipmentID = 0;
		Shipment shipment = null;
		Timestamp timestamp = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm aa");
			Date time = format.parse(datetime);
			timestamp = new java.sql.Timestamp(time.getTime());
		} catch (Exception e) {
			Email.sendExceptionReport(e);
			
		}

		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			String sql = "{Call CreateShipment(?,?,?,?,?,?,?)}";
			stmt = con.prepareCall(sql);
			stmt.setString(1, type);
			stmt.setString(2, number);
			stmt.setString(3, name);
			stmt.setString(4, contact);
			stmt.setTimestamp(5, timestamp);
			stmt.setInt(6, charges);
			stmt.registerOutParameter(7, java.sql.Types.INTEGER);
			stmt.execute();
			shipmentID = stmt.getInt(7);
			shipment = new Shipment(shipmentID, type, name, number, contact,
					timestamp);
			shipment.setCharge(charges);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return shipment;
	}
}
