package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class FundDAO {

	public boolean addLoan(int amount, int tenure, int installment,
			double interest) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into loan(amount, tenure, installment, interest_rate) "
					+ "values(?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setInt(2, tenure);
			stmt.setInt(3, installment);
			stmt.setDouble(4, interest);
			stmt.executeUpdate();
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}

	public static boolean addCapital(int amount, double interest, String lender) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into capital(amount, interest_rate, lender) "
					+ "values(?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setDouble(2, interest);
			stmt.setString(3, lender);
			System.out.println("Interest Rate : " + interest);
			stmt.executeUpdate();

			result = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}
}
