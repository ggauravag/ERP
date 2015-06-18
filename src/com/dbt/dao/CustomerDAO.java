package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class CustomerDAO 
{
	
	
	public static int insertMerchant(Customer customer)
	{ 
		int result = 0;
		Connection con = null;
		CallableStatement stmt = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "Call CreateMerchant(?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareCall(sql);
			String names = customer.getName();
			stmt.setString(1, names.split(" ")[0]);
			stmt.setString(2, names.split(" ")[1]);
			stmt.setString(3, customer.getMobile());
			stmt.setString(4, customer.getEmail());
			Address add = customer.getAddress();
			stmt.setString(5, add.getHouseNo());
			stmt.setString(6,add.getLine1());
			stmt.setString(7, add.getLine2());
			stmt.setString(8, add.getCity());
			stmt.setString(9, add.getState());
			stmt.setString(10, add.getZip());
			stmt.registerOutParameter(11, Types.INTEGER);
			stmt.execute();
			result = stmt.getInt(11);
			
		} catch (NoConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
			e.printStackTrace();
		}
		return result;
	}
	
	public static int insertCustomer(Customer customer)
	{
		Connection con = null;
		CallableStatement stmt = null;
		int cust_id = 0;
		try
		{
			con = DBConnection.getConnection();
			String sql = "Call CreateCustomer(?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareCall(sql);	
			String names = customer.getName();
			stmt.setString(1, names.split(" ")[0]);
			stmt.setString(2, names.split(" ")[1]);
			stmt.setString(3, customer.getMobile());
			stmt.setString(4, customer.getEmail());
			Address add = customer.getAddress();
			stmt.setString(5, add.getHouseNo());
			stmt.setString(6,add.getLine1());
			stmt.setString(7, add.getLine2());
			stmt.setString(8, add.getCity());
			stmt.setString(9, add.getState());
			stmt.setString(10, add.getZip());
			stmt.registerOutParameter(11, Types.INTEGER);
			stmt.execute();
			cust_id = stmt.getInt(11);
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		
		return cust_id;
	}
	
	public static List<Customer> getCustomer(String name)
	{
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try
		{
			System.out.println("CustomerDAO : Name - "+name);
			con = DBConnection.getConnection();
			String query = "select * from customer JOIN address on customer.user_id = address.user_id where name like '%"+name+"%' UNION select * from merchant JOIN address on merchant._id = address.user_id where merchant_name like '%"+name+"%' order by name";
		    //System.out.println("CustomerDAO : Query - "+query);
			stmt = con.prepareStatement(query);
		    set = stmt.executeQuery();
		    while(set.next())
		    {
		    	Address address = new Address(set.getString("house_no"), set.getString("line_1"), set.getString("line_2"), set.getString("city"), set.getString("state"), set.getString("zip"));
		        Customer customer = new Customer(set.getInt("user_id"), set.getString("name"), set.getString("mobile"), set.getString("email"), address);
		        customers.add(customer);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, set);
		}
		
		return customers;
	}
	
}
