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

import org.json.simple.JSONObject;

import com.dbt.data.Address;
import com.dbt.data.Category;
import com.dbt.data.Customer;
import com.dbt.data.Order;
import com.dbt.data.Order_item;
import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class OrderDAO {

	public static void main(String[] args)
	{
		OrderDAO dao = new OrderDAO();
		//dao.getOrderAndPurchaseDetail(0, "Gaurav", new Date(), new Date(), "");
	}
	
	public static boolean takeOrder(Order order) {
		boolean success = false;

		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			String query = "{Call CreateOrder(?,?,?)}";
			stmt = con.prepareCall(query);
			stmt.setInt(1, order.getCustomer().getId());
			stmt.setTimestamp(2, new java.sql.Timestamp(order.getDatetime().getTime()));
			stmt.registerOutParameter(3, java.sql.Types.INTEGER);
			stmt.execute();
			int orderId = stmt.getInt(3);
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
	
	public int addCityInState(String city,String state)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		int result = 2;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("INSERT INTO city(name,state_id) VALUES(?,(SELECT _id FROM state WHERE `name` = ?));");
			stmt.setString(1, city);
			stmt.setString(2, state);
			int numRows = stmt.executeUpdate();
			if(numRows == 1)
			{
				result = 0;
			}
		}
		catch(SQLException e)
		{
			if(e.getSQLState().startsWith("23"))
			{
				result = 1;
			}
			else
				Email.sendExceptionReport(e);
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}

	public List<JSONObject> getOrderAndPurchaseDetail(int id,String name,Date from,Date to, String mobile)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		List<JSONObject> details = new ArrayList<>();
		if(name == "" || name == null)
		{
			name = "-null-";
		}
		
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("SELECT o._id, o.amount, o.discount, concat(u.first_name,' ',u.last_name) AS \"name\", o.time, u.mobile, \"SELL\" AS \"type\" "
								+" FROM `order` o JOIN `user` u ON o.cust_id = u._id"
								+" WHERE concat(u.first_name,' ',u.last_name) LIKE '%"+name+"%' OR u.mobile = ? OR o._id = ? OR (DATE(`time`) >= ? AND DATE(`time`) <= ?)"
								+" UNION"
								+" SELECT p._id, p.amount, 0, m.merchant_name, p.date, m.mobile, \"PURCHASE\" AS \"type\""
								+" FROM `purchase` p JOIN merchant m ON p.merchant_id = m._id"
								+" WHERE m.merchant_name LIKE '%"+name+"%' OR m.mobile = ? OR p._id = ? OR (p.date >= ? AND p.date <= ?)"
								+" ORDER BY 5,1");
			stmt.setInt(2, id);
			stmt.setInt(6, id);
			stmt.setString(1, mobile);
			stmt.setString(5, mobile);
			stmt.setDate(3, new java.sql.Date(from.getTime()));
			stmt.setDate(7, new java.sql.Date(from.getTime()));
			stmt.setDate(4, new java.sql.Date(to.getTime()));
			stmt.setDate(8, new java.sql.Date(to.getTime()));
			System.out.println("Query : "+stmt.toString());
			set = stmt.executeQuery();
			JSONObject object = null;
			while(set.next())
			{
				object = new JSONObject();
				object.put("id", set.getInt("_id"));
				object.put("type", set.getString("type"));
				object.put("amount", set.getInt("amount"));
				object.put("name", set.getString("name"));
				object.put("discount", set.getString("discount"));
				object.put("mobile",set.getString("mobile"));
				object.put("date", set.getString("time"));
				details.add(object);
				//System.out.println(set.getString("name")+","+set.getInt("_id"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, set);
		}
		return details;
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
			stmt.executeUpdate();
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
	}
	
	public void addUpDiscount(int orderId, int discount)
	{
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("UPDATE `order` SET discount = discount + ? WHERE _id = ?");
			stmt.setInt(1, discount);
			stmt.setInt(2, orderId);
			stmt.executeUpdate();
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
				DBConnection.closeResource(con, ps, set);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return order;
	}

	public List<Order> getOrderDetails(String id, String name, String mobile,
			Date to, Date from) {
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
			String sql = "select * from `order` join `user` on order.cust_id = user._id where order._id = ? or concat(user.first_name,' ',last_name) like '" 
					+ name
					+ "' or user.mobile = ? or (DATE(`time`) >= ? and DATE(`time`) <= ?)"
					+ " order by order._id";
			System.out.println("Query : " + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(id));
			stmt.setString(2, mobile);
			stmt.setDate(3, new java.sql.Date(from.getTime()));
			stmt.setDate(4, new java.sql.Date(to.getTime()));
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
				if(res.getString("discount") == null)
				{
					order.setStatus("Not Shipped");
				}
				else
					order.setStatus("Shipped");
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
			System.out.println("Exception Stmt : "+stmt);
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return orders;
	}
	
	public List<Order> getProductsByOrderId(int orderId)
	{
		List<Order> order = new ArrayList<Order>();
		Customer customer = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "select user._id, concat(first_name,' ',last_name) as name, mobile, " +
						 "email, house_no, line_1, line_2, city, state, zip from user join address " +
						 "on user._id = address.user_id and user_id=(select cust_id from `order` " +
						 "where _id=?);";
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderId);
			res = ps.executeQuery();
			if (res.next()) {
				customer = new Customer(res.getInt("_id"), res.getString("name"),
							res.getString("mobile"), res.getString("email"), new Address(
							res.getString("house_no"), res.getString("line_1"),
							res.getString("line_2"), res.getString("city"),
							res.getString("state"), res.getString("zip")));
			}
			
			String query = "select order_id,product_id,order_item.quantity,order_item.amount," +
							"product.name as product_name,ship_id,category.name as category_name from " +
							"order_item,product,category where order_id = ? and product_id = product._id " +
							"and category._id=product.category";
			ps.close();
			res.close();
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			
			res = ps.executeQuery();
			while (res.next()) {
				
				Product p = new Product(res.getInt("product_id"), res.getString("category_name"),
							res.getString("product_name"));
				Order_item oi = new Order_item(orderId, res.getInt("product_id"), 
							res.getInt("quantity"), res.getInt("amount"), res.getInt("ship_id"));
				
				Order or = new Order(customer, p, oi);
				
				order.add(or);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}

		System.out.println("Number of ordered products : " + order.size());

		return order;
	}
	
	public boolean deleteProductById(int productId, int orderId, int amount, int quantity)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		
		boolean result = false;
		try 
		{
			con = DBConnection.getConnection();
			
			String query = "delete from order_item where order_id=? and product_id=?";
			String query1 = "update `order` set amount=amount-? where _id=?;";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, productId);
			
			int r = ps.executeUpdate();
			ps.close();
			ps = con.prepareStatement(query1);
			ps.setInt(1, amount*quantity);
			ps.setInt(2, orderId);
			
			int r1 = ps.executeUpdate();
			
			if(r != 0 && r1 != 0)
			{
				System.out.println("At OrderDAO, product deleted");
				result = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}
		
		return result;
	}
	
	public boolean editOrder(int orderId, int custId, int[] prodIds, int[] prodQtys, int[] prodPrices, int numProd)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		int queryResult[];
		int i, totalAmount=0;
		String query;
		
		boolean result = false;
		try 
		{
			con = DBConnection.getConnection();
			//queryResult = new int[numProd];
			
			query = "update order_item set quantity=quantity+?, amount=amount+? " +
					"where order_id=? and product_id=?";
			
			ps = con.prepareStatement(query);
			
			for(i = 0; i < numProd; i++)
			{
				ps.setInt(1, prodQtys[i]);
				ps.setInt(2, prodPrices[i]);
				ps.setInt(3, orderId);
				ps.setInt(4, prodIds[i]);
				
				ps.addBatch();
			}
			queryResult = ps.executeBatch();
			
			for(i = 0; i < numProd; i++)
			{
				if(queryResult[i] == 0)
				{
					query = "insert into order_item(order_id, product_id, quantity, amount) " +
							"values(?, ?, ?, ?)";
					
					ps = con.prepareStatement(query);
					ps.setInt(1, orderId);
					ps.setInt(2, prodIds[i]);
					ps.setInt(3, prodQtys[i]);
					ps.setInt(4, prodPrices[i]);
					
					ps.executeUpdate();
					
					totalAmount += prodQtys[i] * prodPrices[i];
				}
				else
				{
					totalAmount += prodQtys[i] * prodPrices[i];
				}
			}
			
			query = "update `order` set cust_id=?, amount=amount+? where _id=?";
			ps.close();
			ps = con.prepareStatement(query);
			ps.setInt(1, custId);
			ps.setInt(2, totalAmount);
			ps.setInt(3, orderId);
			
			int r = ps.executeUpdate();
			
			if(r != 0)
			{
				System.out.println("OrderDAO - Order Edited, Order Id : " + orderId);
				result = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}
		
		return result;
	}
	
	public boolean editOrderCustomer(int orderId, int custId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		
		boolean result = false;
		try 
		{
			con = DBConnection.getConnection();
			
			String query = "update `order` set cust_id=? where _id=?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, custId);
			ps.setInt(2, orderId);
			
			int r = ps.executeUpdate();
			
			if(r != 0)
			{
				System.out.print("OrderDAO - Order Customer Edited, Order Id : " + orderId);
				System.out.println(", New Customer Id : " + custId);
				result = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}
		
		return result;
	}
	
	public boolean deleteOrder(int orderId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		
		boolean result = false;
		try 
		{
			con = DBConnection.getConnection();
			
			String query = "delete `order`,order_item from `order` join order_item where " +
						   "order_item.order_id = `order`._id and `order`._id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			
			int r = ps.executeUpdate();
			
			if(r != 0)
			{
				System.out.println("OrderDAO, Order deleted, Order Id : " + orderId);
				result = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, ps, res);
		}
		
		return result;
	}
}

