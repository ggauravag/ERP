package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class AttendanceDAO {

	public boolean getAttendanceToday(String employee_id) {
		boolean result = false;
		System.out.println("AttendanceDAO called");
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		int eid = Integer.parseInt(employee_id);
		try {
			con = DBConnection.getConnection();
			String query = "select * from attendance where `date`=Date(now()) and employee_id="
					+ eid;
			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			if (set.next()) {
				result = true;
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return result;
	}

	public static int MarkAttendance(int employee_id, String tdate, int hday) {
		int result = 0;
		System.out.println("Attendance DAO called");
		Connection con = null;
		CallableStatement stmt = null;
		java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareCall("call insertAttendance(?,?,?,?)");
			stmt.setInt(1, employee_id);
			stmt.setDate(2, new java.sql.Date(df.parse(tdate).getTime()));
			stmt.setInt(3, hday);
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();

			result = stmt.getInt(4);
			stmt.close();
			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		System.out.println("Attendance DAO: Data inserted");
		return result;
	}
}
