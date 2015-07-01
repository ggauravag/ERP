package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dbt.data.Capital;
import com.dbt.data.Loan;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class ExpenditureDAO {
	
	public static List<Loan> getLoanDetails()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		List<Loan> loanDetails = new ArrayList<Loan>();
		String query = "select _id, amount, installment from loan";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while(rst.next())
			{
				loanDetails.add(new Loan(rst.getInt("_id"), rst.getInt("amount"), 0, rst.getInt("installment"), 0));
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
			DBConnection.closeResource(con, ps, rst);
		}

		System.out.println("No of loan details : " + loanDetails.size());
		
		return loanDetails;
	}
	
	public static List<Capital> getInterestDetail()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		List<Capital> interestDetails = new ArrayList<Capital>();
		
		String query = "select _id, amount, lender from capital";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while(rst.next())
			{
				interestDetails.add(new Capital(rst.getInt("_id"), rst.getInt("amount"), 0, rst.getString("lender")));
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
			DBConnection.closeResource(con, ps, rst);
		}

		System.out.println("No of interest details : " + interestDetails.size());
		
		return interestDetails;
	}
	
	public static int addTransaction(int amount, String mode, String desc, String paid)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		int txId = 0;
		
		String query = "insert into transaction(timestamp, amount, mode, paid_by) " +
						"values(now(), ?, ?, ?)";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setString(2, mode + ";" + desc);
			ps.setString(3, paid);
			
			int res = ps.executeUpdate();
			
			if(res == 1)
			{
				String idQuery = "select _id from transaction where amount=? and paid_by=? order by timestamp desc";
				ps = con.prepareStatement(idQuery);
				ps.setInt(1, amount);
				ps.setString(2, paid);
				
				rst = ps.executeQuery();
				
				if(rst.next())
					txId = rst.getInt("_id");
			}
			else
			{
				txId = 0;
				System.out.println("ExpenditureDAO : Error in inserting transaction");
			}
			
			System.out.println("Transaction ID : " + txId);
			
			return txId;
			
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return 0;
	}
	
	public static int addExpenditure(int txId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		int expId = 0;
		
		String query = "insert into expenditure(transaction_id, expenditure_month) " +
					   "values(?, ?)";
		try
		{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, txId);
			ps.setString(2, new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime()));
			
			int res = ps.executeUpdate();
			
			if(res == 1)
			{
				String idQuery = "select _id from expenditure where transaction_id=?";
				ps = con.prepareStatement(idQuery);
				ps.setInt(1, txId);
				
				rst = ps.executeQuery();
				
				while(rst.next())
					expId = rst.getInt("_id");
			}
			else
			{
				expId = 0;
				System.out.println("ExpenditureDAO : Error in inserting expenditure");
			}
			
			System.out.println("Expenditure ID : " + expId);
			
			return expId;
			
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return 0;
	}
	
	public static boolean addDailyExpenditure(int expId, String detail)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		String query = "insert into daily_expenditure(expenditure_id, details) " +
						"values(?, ?)";
		try
		{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setString(2, detail);
			
			int res = ps.executeUpdate();
			
			if(res != 0)
				return true;
			else
			{
				System.out.println("ExpenditureDAO : Error at addDailyExpenditure() ");
				return false;
			}
		
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return false;
	}
	
	public static boolean addLoanExpenditure(int expId, int loanId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		String query = "insert into loan_expenditure(expenditure_id, loan_id) values(?, ?)";
		try
		{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, loanId);
			
			int res = ps.executeUpdate();
			
			if(res != 0)
				return true;
			else
			{
				System.out.println("ExpenditureDAO : Error at addLoanExpenditure() ");
				return false;
			}
		
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return false;
	}
	
	public static boolean addInterestExpenditure(int expId, int capitalId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		String query = "insert into interest_expenditure(expenditure_id, capital_id) values(?, ?)";
		try
		{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, capitalId);
			
			int res = ps.executeUpdate();
			
			if(res != 0)
				return true;
			else
			{
				System.out.println("ExpenditureDAO : Error at addInterestExpenditure()");
				return false;
			}
		
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return false;
	}
	
	public static boolean addSalaryExpenditure(int expId, int empId, String receivedBy)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		String query = "insert into salary_expenditure(expenditure_id, employee_id, recieved_by) " +
						"values(?, ?, ?)";
		try
		{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, empId);
			ps.setString(3, receivedBy);
			
			int res = ps.executeUpdate();
			
			if(res != 0)
				return true;
			else
			{
				System.out.println("ExpenditureDAO : Error at addSalaryExpenditure()");
				return false;
			}
		
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return false;
	}
}
