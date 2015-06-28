package com.dbt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class DBConnection 
{
	
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Email.sendExceptionReport(e);
		}
	}
	
	public static void closeResource(Connection con,PreparedStatement stmt, ResultSet res)
	{
		if(con != null)
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Email.sendExceptionReport(e);
			}
		}
		
		if(res != null)
		{
			try
			{
				res.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Email.sendExceptionReport(e);
			}
		}
		
		if(stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Email.sendExceptionReport(e);
			}
		}
	}
	
	
	public static Connection getConnection() throws NoConnectionException
	{
		Connection con = null;
		try
		{
		   //System.out.println("Local Address is : "+InetAddress.getLocalHost());
		   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ramerp", "root", "rat");
		    //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dreambit_ramerp?noAccessToProcedureBodies=true", "dreambit_root", "#rat123");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if(con == null)
				throw new NoConnectionException();
		}
		return con;
	}
	
}
