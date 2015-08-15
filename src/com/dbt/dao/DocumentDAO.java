package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class DocumentDAO {
	public int getPrintableID(int merchantID, String type, int originalID) {
		int printID = 0;
		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{Call GetDocID(?,?,?,?)}");
			stmt.setInt(1, merchantID);
			stmt.setString(2, type);
			stmt.setInt(3, originalID);
			stmt.registerOutParameter(4, java.sql.Types.INTEGER);
			stmt.execute();
			System.out.println("The value is : "+stmt.getInt(4));
			printID = stmt.getInt(4);
		} catch (Exception ex) {
			
			Email.sendExceptionReport(ex);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
		return printID;
	}
	
	/*public static void main(String[] args)
	{
		DocumentDAO d = new DocumentDAO();
		System.out.println(d.getPrintableID(11, "CHALLAN", 24));
	}*/
}
