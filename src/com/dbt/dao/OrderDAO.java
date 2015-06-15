package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Category;
import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;

public class OrderDAO 
{
	
	
	public static List<Product> getProducts(int id)
	{
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select _id,category,name,quantity,sell_price,cost_price from product where category = ? order by category";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			res = ps.executeQuery();
			while(res.next())
			{
				products.add(new Product(res.getInt("_id"),res.getInt("category"),res.getString("name"),res.getInt("quantity"),res.getInt("sell_price"),res.getInt("cost_price")));
			}
			
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, res);
		}
 
		System.out.println("Number of products : "+products.size());
		return products;
	
	}
	
	
	public List<Category> getAllCategory()
	{
		List<Category> categories = new ArrayList<Category>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from category";
			ps = con.prepareStatement(sql);
			res = ps.executeQuery();
			while(res.next())
			{
				categories.add(new Category(res.getInt("_id"), res.getString("name")));
			}
			
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, res);
		}
 
		System.out.println("Number of categories : "+categories.size());
		return categories;
	}
	
	
	
}
