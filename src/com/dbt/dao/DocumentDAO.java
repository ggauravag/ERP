package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dbt.database.DBConnection;
import com.dbt.support.Document;
import com.dbt.support.Email;

public class DocumentDAO {
	
	public boolean removeDocument(int id)
	{
		boolean result = false;
		Connection con = null;
		PreparedStatement stmt = null;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("delete from uploads where id = ?");
			stmt.setInt(1, id);
			int res = stmt.executeUpdate();
			if(res == 1)
				result = true;
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return result;
	}
	
	public int uploadDocument(String name,String url,String type,int id)
	{
		Connection con = null;
		CallableStatement stmt = null;
		int docId = 0;
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{ Call AddDocument(?,?,?,?,?) }");
			stmt.setString(1, name);
			stmt.setString(2, url);
			stmt.setInt(3, id);
			stmt.setString(4, type);
			stmt.registerOutParameter(5, Types.INTEGER);
			stmt.execute();
			docId = stmt.getInt(5);
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, null);
		}
		return docId;
	}
	
	public List<Document> getDocuments(int id,String type)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		List<Document> documents = new ArrayList<Document>();
		try
		{
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("select id,documentName,uploadURL,documentId,type from uploads where documentId = ? and type = ?");
			stmt.setInt(1, id);
			stmt.setString(2, type);
			set = stmt.executeQuery();
			while(set.next())
			{
				documents.add(new Document(set.getInt("id"), set.getString("uploadURL"), set.getString("documentName"), set.getInt("documentId"), set.getString("type")));
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
		return documents;
	}
	
	
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
