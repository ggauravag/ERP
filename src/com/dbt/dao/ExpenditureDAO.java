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

	public List<Loan> getLoanDetails() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		List<Loan> loanDetails = new ArrayList<Loan>();
		String query = "select _id, amount, installment from loan";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next()) {
				loanDetails.add(new Loan(rst.getInt("_id"), rst
						.getInt("amount"), 0, rst.getInt("installment"), 0));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		System.out.println("No of loan details : " + loanDetails.size());

		return loanDetails;
	}

	public List<Capital> getInterestDetail() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		List<Capital> interestDetails = new ArrayList<Capital>();

		String query = "select _id, amount, lender from capital";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next()) {
				interestDetails.add(new Capital(rst.getInt("_id"), rst
						.getInt("amount"), 0, rst.getString("lender")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		System.out
				.println("No of interest details : " + interestDetails.size());

		return interestDetails;
	}

	public int addTransaction(int amount, String mode, String desc,
			String paid, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		int txId = 0;
		int result = 0;

		String query = "insert into transaction(timestamp, amount, mode, paid_by) "
				+ "values(now(), ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setString(2, mode + ";" + desc);
			ps.setString(3, paid);

			result = ps.executeUpdate();

			if (result == 1) {
				String idQuery = "select _id from transaction where amount=? and paid_by=? order by timestamp desc";
				ps = con.prepareStatement(idQuery);
				ps.setInt(1, amount);
				ps.setString(2, paid);

				rst = ps.executeQuery();

				if (rst.next())
					txId = rst.getInt("_id");

				result = txId;
			} else {
				result = 0;
				System.out
						.println("ExpenditureDAO : Error in inserting transaction");
			}

			System.out.println("Transaction ID : " + txId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public int addExpenditure(int txId, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		int expId = 0;
		int result = 0;

		String query = "insert into expenditure(transaction_id, expenditure_month) "
				+ "values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, txId);
			ps.setString(2, new SimpleDateFormat("MMM").format(Calendar
					.getInstance().getTime()));

			result = ps.executeUpdate();

			if (result == 1) {
				String idQuery = "select _id from expenditure where transaction_id=?";
				ps = con.prepareStatement(idQuery);
				ps.setInt(1, txId);

				rst = ps.executeQuery();

				while (rst.next())
					expId = rst.getInt("_id");

				result = expId;
			} else {
				result = 0;
				System.out
						.println("ExpenditureDAO : Error in inserting expenditure");
			}

			System.out.println("Expenditure ID : " + expId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addDailyExpenditure(int expId, String detail, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into daily_expenditure(expenditure_id, details) "
				+ "values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setString(2, detail);

			int res = ps.executeUpdate();

			if (res != 0)
				result = true;
			else {
				System.out
						.println("ExpenditureDAO : Error at addDailyExpenditure() ");
				result = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addLoanExpenditure(int expId, int loanId, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into loan_expenditure(expenditure_id, loan_id) values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, loanId);

			int res = ps.executeUpdate();

			if (res != 0)
				result = true;
			else {
				System.out
						.println("ExpenditureDAO : Error at addLoanExpenditure() ");
				result = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addInterestExpenditure(int expId, int capitalId,
			Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into interest_expenditure(expenditure_id, capital_id) values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, capitalId);

			int res = ps.executeUpdate();

			if (res != 0)
				result = true;
			else {
				System.out
						.println("ExpenditureDAO : Error at addInterestExpenditure()");
				result = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addSalaryExpenditure(int expId, int empId,
			String receivedBy, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into salary_expenditure(expenditure_id, employee_id, recieved_by) "
				+ "values(?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, expId);
			ps.setInt(2, empId);
			ps.setString(3, receivedBy);

			int res = ps.executeUpdate();

			if (res == 1)
				result = true;
			else {
				System.out
						.println("ExpenditureDAO : Error at addSalaryExpenditure()");
				result = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addPurchaseExpenditure(int expId, int purId, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into purchase_expenditure(purchase_id,expenditure_id) "
				+ "values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, purId);
			ps.setInt(2, expId);

			int res = ps.executeUpdate();

			if (res == 1)
				result = true;
			else {
				System.out
						.println("ExpenditureDAO : Error at addSalaryExpenditure()");
				result = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}

		return result;
	}

	public boolean addExpenditureDetail(int amount, String mode,
			String description, String paid, String dynamic, String expType,
			String receivedBy, String empId) {
		Connection con = null;

		boolean status = false;
		boolean result = false;
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			int txId = addTransaction(amount, mode, description, paid, con);
			int expId = addExpenditure(txId, con);

			if (expType.equals("salary"))
				status = addSalaryExpenditure(expId, Integer.parseInt(empId),
						receivedBy, con);

			else if (expType.equals("daily"))
				status = addDailyExpenditure(expId, dynamic, con);

			else if (expType.equals("loan"))
				status = addLoanExpenditure(expId, Integer.parseInt(dynamic),
						con);

			else if (expType.equals("interest"))
				status = addInterestExpenditure(expId,
						Integer.parseInt(dynamic), con);
			else if (expType.equals("purchase"))
				status = addPurchaseExpenditure(expId,
						Integer.parseInt(dynamic), con);
			System.out.print("Tx Id : " + txId + ", Exp Id : " + expId
					+ ", Status = " + status);

			if (status == false || txId == 0 || expId == 0) {
				con.rollback();
				System.out.print("ExpenditureDAO : Unable to add " + expType
						+ " expenditure.");
				result = false;
			} else {
				con.commit();
				System.out.print("ExpenditureDAO : " + expType
						+ " expenditure added.");
				result = true;
			}

		} catch (Exception ex) {
			Email.sendExceptionReport(ex);
		} finally {
			DBConnection.closeResource(con, null, null);
		}
		return result;
	}
}
