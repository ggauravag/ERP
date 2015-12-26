package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbt.data.Transaction;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class TransactionDAO {
	
	public static void main(String args[])
	{
		new TransactionDAO().getTransaction(1, "Gaurav", "998216368", new Date(), new Date());
	}
	
	public List<Transaction> getTransaction(int id, String name, String mobile, Date from,
			Date to) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Transaction> transactions = null;
		if(name.equals(""))
			name = "null";
		try 
		{
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select p.order_id,p.type,t._id,t.amount,t.mode,t.paid_by,t.timestamp "
							+ "from payment p join `transaction` t on p.transaction_id = t._id "
							+ "join `order` o on o._id = p.order_id "
							+ "join `user` u on u._id = o.cust_id "
							+ "where concat(u.first_name,' ',u.last_name) like '%"
							+ name
							+ "%' or t._id = ? or u.mobile = ? or (DATE(t.timestamp) >= ? and DATE(t.timestamp) <= ?) "
							+ "union"
							+ " select e._id,'EXPENDITURE',t._id,t.amount,t.mode,t.paid_by,t.timestamp"
							+ " from expenditure e "
							+ " join `transaction` t on e.transaction_id = t._id"
							+ " "
							+ " where (DATE(t.timestamp) >= ? and DATE(t.timestamp) <= ?) or t._id = ? order by 7,3");
			
			stmt.setInt(1, id);
			stmt.setString(2, mobile);
			stmt.setDate(3, new java.sql.Date(from.getTime()));
			stmt.setDate(4, new java.sql.Date(to.getTime()));
			stmt.setDate(5, new java.sql.Date(from.getTime()));
			stmt.setDate(6, new java.sql.Date(to.getTime()));
			stmt.setInt(7, id);
			//System.out.println(stmt);
			ResultSet set = stmt.executeQuery();
			transactions = new ArrayList<Transaction>();
			while (set.next()) 
			{
				String modeDesc = set.getString("mode");
				String[] values = modeDesc.split(";");
				if(values.length == 1)
				{
					String[] v = values.clone();
					values = new String[2];
					values[0] = v[0];
					values[1] = "None";
				}
				Transaction tran = new Transaction(set.getInt("order_id"), set.getInt("_id"), set.getInt("amount"), set.getString("paid_by"),values[0],values[1], new Date(set.getTimestamp("timestamp").getTime()), set.getString("type"));
				transactions.add(tran);
			}
		} 
		catch (Exception e) 
		{
			Email.sendExceptionReport(e);
		} 
		finally 
		{
			DBConnection.closeResource(con, stmt, res);
		}
		return transactions;
	}

}
