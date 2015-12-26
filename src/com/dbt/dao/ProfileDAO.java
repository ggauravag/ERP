package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class ProfileDAO {

	public int updateMobile(String mobile, int uid) {
		int result = 0;
		System.out.println("Profile DAO called");
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("update user set mobile=? where _id=?");
			stmt.setString(1, mobile);
			stmt.setInt(2, uid);
			result = stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		System.out.println("Profile DAO: Data updated successfully");
		return result;
	}

	public int updatePass(String pass, int uid) {
		int result = 0;
		// System.out.println("Profile DAO called");
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("update user_login set password=? where user_id=?");
			stmt.setString(1, pass);
			stmt.setInt(2, uid);
			result = stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		// System.out.println("Profile DAO: Data updated successfully");
		return result;
	}

	public int updateName(String fname, String lname, int uid) {
		int result = 0;
		// System.out.println("Profile DAO called");
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("update user set first_name=?,last_name=? where _id=?");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setInt(3, uid);
			result = stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		// System.out.println("Profile DAO: Data updated successfully");
		return result;
	}
}
