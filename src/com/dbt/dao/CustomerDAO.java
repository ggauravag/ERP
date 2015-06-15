package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class CustomerDAO 
{
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
