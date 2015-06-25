package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dbt.data.Category;
import com.dbt.data.Customer;
import com.dbt.data.Order;
import com.dbt.data.Order_item;
import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class OrderDAO {
	
	

	public static boolean takeOrder(Order order) {
		boolean success = false;

		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			String query = "{Call CreateOrder(?,?)}";
			stmt = con.prepareCall(query);
			stmt.setInt(1, order.getCustomer().getId());
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);
			stmt.execute();
			int orderId = stmt.getInt(2);
			order.setId(orderId);
			stmt.close();
			Iterator<Product> products = order.getProducts().iterator();
			String sql = "insert into order_item values(order_id,product_id,quantity,amount) ";
			while (products.hasNext()) {
				products.next();
				if (products.hasNext())
					sql += "(?,?,?,?), ";
				else
					sql += "(?,?,?,?);";
			}

			products = order.getProducts().iterator();
			int i = 1, amount = 0;
			PreparedStatement ps = con.prepareStatement(sql);
			while (products.hasNext()) {
				Product p = products.next();
				ps.setInt(i++, orderId);
				ps.setInt(i++, p.getId());
				ps.setInt(i++, p.getQuantity());
				ps.setInt(i++, p.getSellPrice());
				amount += p.getQuantity() * p.getSellPrice();
			}

			int j = ps.executeUpdate();
			if (j >= 1) {
				success = true;
				sql = "update `order` set amount = ? where _id = ?";
				ps.close();
				ps = con.prepareStatement(sql);
				ps.setInt(1, amount);
				ps.setInt(2, orderId);
				ps.executeUpdate();
				ps.close();
				order.setAmount(amount);
			} else
				success = false;

		} catch (NoConnectionException | SQLException ex) {
			Email.sendExceptionReport(ex);
			ex.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return success;
	}

	public static List<Product> getProducts(int id) {
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
			while (res.next()) {
				products.add(new Product(res.getInt("_id"), res
						.getInt("category"), res.getString("name"), res
						.getInt("quantity"), res.getInt("sell_price"), res
						.getInt("cost_price")));
			}

		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}

		System.out.println("Number of products : " + products.size());
		return products;

	}

	public List<Category> getAllCategory() {
		List<Category> categories = new ArrayList<Category>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from category";
			ps = con.prepareStatement(sql);
			res = ps.executeQuery();
			while (res.next()) {
				categories.add(new Category(res.getInt("_id"), res
						.getString("name")));
			}

		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}

		System.out.println("Number of categories : " + categories.size());
		return categories;
	}

	public static List<Order> getOrderDetails(String id, String name,
			String mobile) {
		List<Order> orders = new ArrayList<Order>();
		if(name != null && !"".equals(name))
			name = "%" + name + "%";
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		if (id == null || "".equals(id))
			id = "0";

		try {
			con = DBConnection.getConnection();
			String sql = "select * from `order` join `user` on order.cust_id = user._id where order._id = "
					+ id
					+ " or concat(user.first_name,' ',last_name) like '"
					+ name + "' or user.mobile = '" + mobile + "'";
			System.out.println("Query : "+sql);
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();
			Order order = null;
			while (res.next()) {
				order = new Order(res.getInt("_id"), res.getInt("amount"),
						new Customer(res.getInt("cust_id"),
								res.getString("first_name") + " "
										+ res.getString("last_name"),
								res.getString("mobile"),
								res.getString("email"), null,
								res.getString("type")), res.getDate("time"));
				String s = "select order_id,product_id,order_item.quantity,order_item.amount,category,name,ship_id from order_item,product where order_id = "
						+ res.getInt("_id") + " and product_id = product._id";
				PreparedStatement ps = con.prepareStatement(s);
				ResultSet set = ps.executeQuery();
				List<Order_item> products = new ArrayList<Order_item>();
				while (set.next()) {
					Order_item item = new Order_item(set.getInt("order_id"),
							set.getInt("product_id"), set.getInt("quantity"),
							set.getInt("amount"));
					item.setProduct_name(set.getString("name"));
					if (set.getString("ship_id") == null) {
						item.setShip_id(0);
					} else
						item.setShip_id(set.getInt("ship_id"));
					products.add(item);
				}
				order.setOrderitems(products);
				DBConnection.closeResource(null, ps, set);
				orders.add(order);
			}

		} catch (NoConnectionException e) {
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} catch (SQLException e) {
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, stmt, res);
		}

		return orders;
	}

}
