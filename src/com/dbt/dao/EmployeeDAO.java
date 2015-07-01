package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.User;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class EmployeeDAO {

	public static List<User> getEmployee()
	{
		List<User> employees = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		try
		{
			System.out.println("In EmployeeDAO");
			con = DBConnection.getConnection();
			String query = "select _id, first_name, last_name, mobile from user where type = ?";
		    //System.out.println("CustomerDAO : Query - "+query);
			ps = con.prepareStatement(query);
			ps.setString(1, "EMPLOYEE");
			
		    rst = ps.executeQuery();
		    
		    while(rst.next())
		    {
		    	User employee = new User();
		    	employee.setId(rst.getInt("_id"));
		    	employee.setFirstName(rst.getString("first_name"));
		    	employee.setLastName(rst.getString("last_name"));
		    	employee.setMobile(rst.getString("mobile"));
		    	
		    	employees.add(employee);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, ps, rst);
		}
		
		return employees;
	}
	
}
