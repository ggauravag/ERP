package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;
import com.dbt.vo.Shipment;

public class ShipmentDAO {

	public Shipment getShipmentByID(int shipID) {
		Shipment shipment = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select _id,medium,charge,medium_no,loader,contact,send_date from shipment where _id = ?");
			stmt.setInt(1, shipID);
			res = stmt.executeQuery();
			if (res.next()) {
				shipment = new Shipment(res.getInt("_id"),
						res.getString("medium"), res.getString("loader"),
						res.getString("medium_no"), res.getString("contact"),
						res.getTimestamp("send_date"));
				shipment.setCharge(res.getInt("charge"));
				PreparedStatement ps = con
						.prepareStatement("select _id,category,name,order_item.quantity,amount from order_item join product on order_item.product_id = product._id where ship_id = ?");
				ps.setInt(1, shipment.getId());
				ResultSet set = ps.executeQuery();
				List<Product> products = new ArrayList<Product>();
				while (set.next()) {
					products.add(new Product(set.getInt("_id"), set
							.getInt("category"), set.getString("name"), set
							.getInt("quantity"), set.getInt("amount"), 0));
				}
				shipment.setItems(products);
				DBConnection.closeResource(null, ps, set);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return shipment;
	}

	public List<Shipment> getShipment(int orderID) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select _id,medium,medium_no,loader,contact,send_date from shipment s join order_item o on s._id = o.ship_id where o.order_id = ? group by s._id");
			stmt.setInt(1, orderID);
			res = stmt.executeQuery();
			while (res.next()) {
				Shipment shipment = new Shipment(res.getInt("_id"),
						res.getString("medium"), res.getString("loader"),
						res.getString("medium_no"), res.getString("contact"),
						res.getTimestamp("send_date"));
				shipments.add(shipment);
				PreparedStatement ps = con
						.prepareStatement("select _id,category,name,order_item.quantity,amount from order_item join product on order_item.product_id = product._id where ship_id = ?");
				ps.setInt(1, shipment.getId());
				ResultSet set = ps.executeQuery();
				List<Product> products = new ArrayList<>();
				while (set.next()) {
					products.add(new Product(set.getInt("_id"), set
							.getInt("category"), set.getString("name"), set
							.getInt("quantity"), set.getInt("amount"), 0));
				}
				shipment.setItems(products);
				DBConnection.closeResource(null, ps, set);
			}
		} catch (NoConnectionException e) {

			Email.sendExceptionReport(e);
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return shipments;
	}

}
