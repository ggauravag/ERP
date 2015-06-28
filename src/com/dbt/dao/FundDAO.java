package com.dbt.dao;

import java.sql.Connection;
import java.sql.SQLException;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

import java.sql.PreparedStatement;

public class FundDAO {

	public static boolean addLoan(int amount, int tenure, int installment, int interest)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "insert into loan(amount, tenure, installment, interest_rate) " +
							"values(?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setInt(2, tenure);
			stmt.setInt(3, installment);
			stmt.setInt(4, interest);
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return false;
	}
	
	public static boolean addCapital(int amount, int interest, String lender)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "insert into capital(amount, interest_rate, lender) " +
							"values(?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setInt(2, interest);
			stmt.setString(3, lender);
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return false;
	}
}
