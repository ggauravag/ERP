package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.dbt.data.Address;
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
			String sql = "insert into order_item(order_id,product_id,quantity,amount) values";
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

		} catch (Exception ex) {
			Email.sendExceptionReport(ex);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return success;
	}

	public List<String> getCity(String state) {
		List<String> cities = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select city.name from city join state on state_id = state._id where state.name = ? order by city.name");
			stmt.setString(1, state);
			res = stmt.executeQuery();
			while (res.next()) {
				cities.add(res.getString("name"));
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return cities;
	}

	public List<String> getStates() {
		List<String> states = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("select * from state order by name");
			res = stmt.executeQuery();
			while (res.next()) {
				states.add(res.getString("name"));
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		System.out.println("Total Number of States : " + states.size());

		return states;
	}

	public List<Product> getProducts(int id) {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select _id,category,name,quantity,sell_price,cost_price from product where category = ? order by name";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			res = ps.executeQuery();
			while (res.next()) {
				products.add(new Product(res.getInt("_id"), res
						.getInt("category"), res.getString("name"), res
						.getInt("quantity"), res.getInt("sell_price"), res
						.getInt("cost_price")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
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
			String sql = "select * from category order by name";
			ps = con.prepareStatement(sql);
			res = ps.executeQuery();
			while (res.next()) {
				categories.add(new Category(res.getInt("_id"), res
						.getString("name")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, res);
		}

		System.out.println("Number of categories : " + categories.size());
		return categories;
	}

	public void addDiscount(int orderID, int discount) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("update `order` set discount = ? where _id = ?");
			stmt.setInt(1, discount);
			stmt.setInt(2, orderID);
			int i = stmt.executeUpdate();
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
	}

	public Order getOrder(int id) {
		Order order = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select (select tin from merchant where merchant._id = user._id) as tin,house_no,line_1,line_2,city,state,zip,order._id,cust_id,amount,time,discount,concat(first_name,' ',last_name) as name,email,mobile,type from `order` join `user` join address on order.cust_id = user._id and address.user_id = user._id where order._id = ?");
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if (res.next()) {
				order = new Order(new Customer(res.getInt("cust_id"),
						res.getString("name"), res.getString("mobile"),
						res.getString("email"), new Address(
								res.getString("house_no"),
								res.getString("line_1"),
								res.getString("line_2"), res.getString("city"),
								res.getString("state"), res.getString("zip")),
						res.getString("type"), res.getString("tin")), null, id,
						res.getDate("time"), res.getInt("amount"));
				order.setDiscount(res.getInt("discount"));
				PreparedStatement ps = con
						.prepareStatement("select _id,name,category,amount,order_item.quantity from order_item join product on order_item.product_id = product._id and order_item.order_id = ?");
				ps.setInt(1, id);
				ResultSet set = ps.executeQuery();
				List<Order_item> products = new ArrayList<Order_item>();
				while (set.next()) {
					Order_item item = new Order_item(id, set.getInt("_id"),
							set.getInt("quantity"), set.getInt("amount"));
					item.setProduct_name(set.getString("name"));
					products.add(item);
				}
				order.setOrderitems(products);
				DBConnection.closeResource(null, ps, set);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return order;
	}

	public List<Order> getOrderDetails(String id, String name, String mobile) {
		List<Order> orders = new ArrayList<Order>();
		if (name != null && !"".equals(name))
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
					+ name
					+ "' or user.mobile = '"
					+ mobile
					+ "' order by order._id";
			System.out.println("Query : " + sql);
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
								res.getString("type")), new Date(res
								.getTimestamp("time").getTime()));
				// System.out.println("Order Datetime is : "+res.getTimestamp("time")+",  ID is : "+res.getInt("_id"));
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

		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return orders;
	}

}
