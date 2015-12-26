package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class CustomerDAO {

	public int insertMerchant(Customer customer) {
		int result = 0;
		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DBConnection.getConnection();
			String sql = "Call CreateMerchant(?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareCall(sql);
			String name = customer.getName();
			String[] names = name.split(" ");
			stmt.setString(1, names[0]);
			StringBuilder restName = new StringBuilder("");
			for (int i = 1; i < names.length; i++)
				restName.append(names[i] + " ");

			stmt.setString(2, restName.toString());
			stmt.setString(3, customer.getMobile());
			if (!"".equals(customer.getEmail())) {
				stmt.setString(4, customer.getEmail());
			} else {
				stmt.setString(4, null);
			}

			Address add = customer.getAddress();
			stmt.setString(5, customer.getTin());
			stmt.setString(6, add.getHouseNo());
			stmt.setString(7, add.getLine1());
			stmt.setString(8, add.getLine2());
			stmt.setString(9, add.getCity());
			stmt.setString(10, add.getState());
			stmt.setString(11, add.getZip());
			stmt.registerOutParameter(12, Types.INTEGER);
			stmt.execute();
			result = stmt.getInt(12);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}

	public int insertCustomer(Customer customer) {
		Connection con = null;
		CallableStatement stmt = null;
		int cust_id = 0;
		try {
			con = DBConnection.getConnection();
			String sql = "Call CreateCustomer(?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareCall(sql);
			String name = customer.getName();
			String[] names = name.split(" ");
			stmt.setString(1, names[0]);
			StringBuilder restName = new StringBuilder("");
			for (int i = 1; i < names.length; i++)
				restName.append(names[i] + " ");
			stmt.setString(2, restName.toString());
			stmt.setString(3, customer.getMobile());
			if (!"".equals(customer.getEmail())) {
				stmt.setString(4, customer.getEmail());
			} else {
				stmt.setString(4, null);
			}
			Address add = customer.getAddress();
			stmt.setString(5, add.getHouseNo());
			stmt.setString(6, add.getLine1());
			stmt.setString(7, add.getLine2());
			stmt.setString(8, add.getCity());
			stmt.setString(9, add.getState());
			stmt.setString(10, add.getZip());
			stmt.registerOutParameter(11, Types.INTEGER);
			stmt.execute();
			cust_id = stmt.getInt(11);
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return cust_id;
	}

	public List<Customer> getCustomer(String name) {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			System.out.println("CustomerDAO : Name - " + name);
			con = DBConnection.getConnection();
			String query = "select user_id,(select tin from merchant where _id = user_id) as tin,concat(first_name,' ',last_name) as name,mobile,email,house_no,line_1,line_2,city,state,zip,type from user JOIN address on user._id = address.user_id where lower(concat(first_name,' ',last_name)) like '%"
					+ name.toLowerCase()
					+ "%' and user.type in ('CUSTOMER','MERCHANT') order by name ";
			// System.out.println("CustomerDAO : Query - "+query);
			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			while (set.next()) {
				Address address = new Address(set.getString("house_no"),
						set.getString("line_1"), set.getString("line_2"),
						set.getString("city"), set.getString("state"),
						set.getString("zip"));
				Customer customer = new Customer(set.getInt("user_id"),
						set.getString("name"), set.getString("mobile"),
						set.getString("email"), address, set.getString("type"),
						set.getString("tin"));
				customers.add(customer);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return customers;
	}
	
	public List<Customer> getCustomerById(int id) {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			System.out.println("CustomerDAO : ID - " + id);
			con = DBConnection.getConnection();
			String query = "select user_id,(select tin from merchant where _id = user_id) as tin,concat(first_name,' ',last_name) as name,mobile,email,house_no,line_1,line_2,city,state,zip,type from user JOIN address on user._id = address.user_id where user._id = "
					+ id
					+ " and user.type in ('CUSTOMER','MERCHANT') ";
			// System.out.println("CustomerDAO : Query - "+query);
			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			while (set.next()) {
				Address address = new Address(set.getString("house_no"),
						set.getString("line_1"), set.getString("line_2"),
						set.getString("city"), set.getString("state"),
						set.getString("zip"));
				Customer customer = new Customer(set.getInt("user_id"),
						set.getString("name"), set.getString("mobile"),
						set.getString("email"), address, set.getString("type"),
						set.getString("tin"));
				customers.add(customer);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return customers;
	}

	public List<Customer> getCustomersByNameMob(String name, String mob) {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			System.out.println("CustomerDAO : Name - " + name);
			con = DBConnection.getConnection();
			String query = "";

			if (name != null && name != "" && mob != null && mob != "") {
				query = "select user_id,(select tin from merchant where _id = user_id) as tin,concat(first_name,' ',last_name) as name,mobile,email,house_no,line_1,line_2,city,state,zip,type from user JOIN address on user._id = address.user_id where lower(concat(first_name,' ',last_name)) like '"
						+ name.toLowerCase()
						+ "%' and mobile like '"
						+ mob
						+ "%' and user.type in ('CUSTOMER','MERCHANT') ";
			} else if (name != null && name != "") {
				query = "select user_id,(select tin from merchant where _id = user_id) as tin,concat(first_name,' ',last_name) as name,mobile,email,house_no,line_1,line_2,city,state,zip,type from user JOIN address on user._id = address.user_id where lower(concat(first_name,' ',last_name)) like '"
						+ name.toLowerCase()
						+ "%' and user.type in ('CUSTOMER','MERCHANT') ";
			} else if (mob != null && mob != "") {
				query = "select user_id,(select tin from merchant where _id = user_id) as tin,concat(first_name,' ',last_name) as name,mobile,email,house_no,line_1,line_2,city,state,zip,type from user JOIN address on user._id = address.user_id where mobile like '"
						+ mob + "%' and user.type in ('CUSTOMER','MERCHANT') ";
			} else {
				return customers;
			}

			System.out.println("CustomerDAO : Query - " + query);
			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			while (set.next()) {
				Address address = new Address(set.getString("house_no"),
						set.getString("line_1"), set.getString("line_2"),
						set.getString("city"), set.getString("state"),
						set.getString("zip"));
				Customer customer = new Customer(set.getInt("user_id"),
						set.getString("name"), set.getString("mobile"),
						set.getString("email"), address, set.getString("type"),
						set.getString("tin"));
				customers.add(customer);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return customers;
	}

}
