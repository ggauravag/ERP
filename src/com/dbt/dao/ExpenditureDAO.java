package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.dbt.data.Capital;
import com.dbt.data.Expenditure;
import com.dbt.data.Loan;
import com.dbt.data.Transaction;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;
import com.squareup.okhttp.Call;

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
				ps.close();
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
				ps.close();
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
	
	public static void main(String[] args)
	{
		new ExpenditureDAO().addExpenditureDetail(5555, "Cash", "This is sample description.", "Gaurav Agarwal", "5", "salary", "Arjun Ji", "24");
	}

	public boolean addExpenditureDetail(int amount, String mode,
			String description, String paid, String dynamic, String expType,
			String receivedBy, String empId) 
	{
		Connection con = null;
		CallableStatement stmt = null;
		boolean status = false;
		boolean result = false;
		try 
		{
			if(!expType.equals("salary"))
				empId = "0";
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{Call AddExpenditure(?,?,?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, amount);
			stmt.setString(2, mode);
			stmt.setString(3, description);
			stmt.setString(4, paid);
			stmt.setString(5, dynamic);
			stmt.setString(6, expType);
			stmt.setString(7, receivedBy);
			stmt.setInt(8, Integer.parseInt(empId));
			stmt.registerOutParameter(9, Types.INTEGER);
			stmt.registerOutParameter(10, Types.INTEGER);
			stmt.execute();
			int txId = stmt.getInt(10);
			int expId = stmt.getInt(9);
			if(txId != 0 && expId != 0)
				status = true;
			/*con.setAutoCommit(false);

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
						Integer.parseInt(dynamic), con);*/
			System.out.print("Tx Id : " + txId + ", Exp Id : " + expId
					+ ", Status = " + status);

			if (status == false || txId == 0 || expId == 0) 
			{
				System.out.print("ExpenditureDAO : Unable to add " + expType + " expenditure.");
				result = false;
			}
			else 
			{
				System.out.print("ExpenditureDAO : " + expType + " expenditure added.");
				result = true;
			}
		} 
		catch (Exception ex) 
		{
			Email.sendExceptionReport(ex);
		} 
		finally 
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}
	
	public Expenditure getExpenditureById(int transactID,int expenditureID)
	{
		Expenditure exp = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("select t._id,'SALARY' as 'type',e._id as 'exp_id',t.amount,t.mode,t.paid_by,t.timestamp,concat(u.first_name,u.last_name) as 'detail'"
					+" from expenditure e join `transaction` t on e.transaction_id = t._id join salary_expenditure se on e._id = se.expenditure_id join employee em on em.employee_id = se.employee_id join `user` u on u._id = em.employee_id"
					+" where e._id = ? and t._id = ?"
					+" union"
					+" select t._id,'DAILY' as 'type',e._id as 'exp_id',t.amount,t.mode,t.paid_by,t.timestamp,de.details as 'detail'"
					+" from expenditure e join `transaction` t on e.transaction_id = t._id join daily_expenditure de on de.expenditure_id = e._id"
					+" where e._id = ? and t._id = ?"
					+" union"
					+" select t._id,'LOAN' as 'type',e._id as 'exp_id',t.amount,t.mode,t.paid_by,t.timestamp,concat(l._id,'|',l.amount,'|',l.tenure,'|',l.installment,'|',l.interest_rate) as 'detail'"
					+" from expenditure e join `transaction` t on e.transaction_id = t._id join loan_expenditure de on de.expenditure_id = e._id join loan l on l._id = de.loan_id"
					+" where e._id = ? and t._id = ? union"
					+" select t._id,'PURCHASE' as 'type',e._id as 'exp_id',t.amount,t.mode,t.paid_by,t.timestamp,concat(m.merchant_name,'|',m.mobile,'|',p.amount,'|',p.date) as 'detail'"
					+" from expenditure e join `transaction` t on e.transaction_id = t._id join purchase_expenditure de on de.expenditure_id = e._id join purchase p on de.purchase_id = p._id join merchant m on m._id = p.merchant_id"
					+" where e._id = ? and t._id = ?"
					+" union"
					+" select t._id,'INTEREST' as 'type',e._id as 'exp_id',t.amount,t.mode,t.paid_by,t.timestamp,concat(l._id,'|',l.amount,'|',l.interest_rate,'|',l.lender) as 'detail'"
					+" from expenditure e join `transaction` t on e.transaction_id = t._id join interest_expenditure de on de.expenditure_id = e._id join capital l on l._id = de.capital_id"
					+" where e._id = ? and t._id = ?");
			
			stmt.setInt(1, expenditureID);
			stmt.setInt(2, transactID);
			stmt.setInt(3, expenditureID);
			stmt.setInt(4, transactID);
			stmt.setInt(5, expenditureID);
			stmt.setInt(6, transactID);
			stmt.setInt(7, expenditureID);
			stmt.setInt(8, transactID);
			stmt.setInt(9, expenditureID);
			stmt.setInt(10, transactID);
			System.out.println(stmt);
			set = stmt.executeQuery();
			if(set.next())
			{
				String[] values = set.getString("mode").split(";");
				if(values.length == 1)
				{
					String[] v = values.clone();
					values = new String[2];
					values[0] = v[0];
					values[1] = "None";
				}
				Transaction trans = new Transaction(set.getInt("exp_id"), set.getInt("_id"), set.getInt("amount"), set.getString("paid_by"), values[0], values[1], new Date(set.getTimestamp("timestamp").getTime()), "EXPENDITURE");
				exp = new Expenditure(set.getString("type"), set.getInt("exp_id"), trans, set.getString("detail"));
			}
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, set);
		}
		return exp;
	}
}
