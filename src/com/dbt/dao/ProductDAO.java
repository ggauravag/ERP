package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class ProductDAO {
	public Product getProductQuantity(int prodID) {
		Product product = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			con = DBConnection.getConnection();
			String sql = "select * from product where _id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodID);
			res = stmt.executeQuery();
			if (res.next()) {
				product = new Product(res.getInt("_id"),
						res.getInt("category"), res.getString("name"),
						res.getInt("quantity"), res.getInt("sell_price"),
						res.getInt("cost_price"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return product;
	}
}
