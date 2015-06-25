package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dbt.data.Order_item;
import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class OrderProcessDAO {
	
	public void shipProducts(int orderID,int[] prodID,String type, String name, String number,
			String contact, String datetime)
	{
		int shipID = addShipment(type, name, number, contact, datetime);
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBConnection.getConnection();
			StringBuffer cond = new StringBuffer("");
			for(int k = 0; k < prodID.length; k++)
			{
				if(k != prodID.length - 1)
					cond.append(prodID[k]+",");
				else
					cond.append(prodID[k]);
			}
			
			String sql = "update order_item set ship_id = ? where order_id = ? and product_id in ("+cond.toString()+")";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, shipID);
			stmt.setInt(2, orderID);
			stmt.executeUpdate();
		
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		
		
	}
	
	
	public int addShipment(String type, String name, String number,
			String contact, String datetime) {
		int shipmentID = 0;
		Timestamp timestamp = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm aa");
			Date time = format.parse(datetime);
			timestamp = new java.sql.Timestamp(time.getTime());
		} catch (Exception e) {
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}

		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			String sql = "{Call CreateShipment(?,?,?,?,?,?)}";
			stmt = con.prepareCall(sql);
			stmt.setString(1, type);
			stmt.setString(2, number);
			stmt.setString(3, name);
			stmt.setString(4, contact);
			stmt.setTimestamp(5, timestamp);
			stmt.registerOutParameter(6, java.sql.Types.INTEGER);
			stmt.execute();
			shipmentID = stmt.getInt(6);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}

		return shipmentID;
	}
}
