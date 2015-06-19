package com.dbt.dao;

import java.sql.Connection;

import com.dbt.database.DBConnection;
import com.dbt.support.Email;
import com.mysql.jdbc.PreparedStatement;

public class ProductDAO {
	
	public static int insertProduct()
	{
		Connection con = null;
		PreparedStatement stmt = null;

		try
		{
			String sql = "";
			con = DBConnection.getConnection();
			
			
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		
		return 0;
	}

}
