package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class ManageCustomerDAO {

	public boolean updateCustomerData(String name, String email, String mobile,
			String line1, String line2, String house, String city,
			String state, String zip, String tin, int id, String type) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "update user set first_name=?, last_name=?, email=?, mobile=? where _id=?";
		try {

			String lname = "";
			String[] names = name.split(" ");
			for (int i = 1; i < names.length; i++)
				lname += names[i];

			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, names[0]);
			ps.setString(2, lname);
			if ("".equals(email) || email == null) {
				ps.setNull(3, java.sql.Types.VARCHAR);
			} else {
				ps.setString(3, email);
			}
			ps.setString(4, mobile);
			ps.setInt(5, id);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("User Table updated");
				if (type.equals("MERCHANT")) {
					String query1 = "update merchant set merchant_name=?, mobile=?, email=?, tin=? where _id=?";
					ps.close();
					ps = con.prepareStatement(query1);
					ps.setString(1, name);
					ps.setString(2, mobile);
					if ("".equals(email) || email == null) {
						ps.setNull(3, java.sql.Types.VARCHAR);
					} else {
						ps.setString(3, email);
					}

					ps.setString(4, tin);
					ps.setInt(5, id);
				} else {
					String query1 = "update customer set name=?, mobile=?, email=? where user_id=?";
					ps.close();
					ps = con.prepareStatement(query1);
					ps.setString(1, name);
					ps.setString(2, mobile);
					if ("".equals(email) || email == null) {
						ps.setNull(3, java.sql.Types.VARCHAR);
					} else {
						ps.setString(3, email);
					}
					ps.setInt(4, id);
				}
				ps.executeUpdate();

				updateAddress(house, line1, line2, city, state, zip, con, id);

				result = true;
			} else {
				System.out.println("User Table not updated");
				result = false;
			}

			System.out.println("Details Updated for Customer ID : " + id
					+ ", Name : " + name);

		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		return result;
	}

	public boolean updateAddress(String house, String line1, String line2,
			String city, String state, String pin, Connection con, int id) {
		boolean result = false;
		PreparedStatement ps = null;
		ResultSet rst = null;

		String query = "update address set house_no=?, line_1=?, line_2=?, city=?, state=?, zip=? where user_id=?";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, house);
			ps.setString(2, line1);
			ps.setString(3, line2);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, pin);
			ps.setInt(7, id);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("Address Updated, City : " + city
						+ ", State : " + state);
				result = true;
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		return result;
	}

	public boolean deleteCustomer(int id, String type) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "";

		try {
			con = DBConnection.getConnection();

			if (type.equals("MERCHANT")) {
				query = "delete user,address,merchant from user join address on user._id = address.user_id join "
						+ "merchant on address.user_id = merchant._id where user._id = ?";
			} else if (type.equals("CUSTOMER")) {
				query = "delete user,address,customer from user join address on user._id = address.user_id join "
						+ "customer on address.user_id = customer.user_id where user._id = ?";
			}

			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			int res = ps.executeUpdate();

			if (res != 0) {
				System.out.println("Customer deleted for id : " + id);
				result = true;
			} else
				System.out.println("Unable to delete customer for id : " + id);
		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, ps, rst);
		}
		return result;
	}
}
