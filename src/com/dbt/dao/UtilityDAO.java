package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbt.data.Reminder;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class UtilityDAO {

	public void addReminder(String message, String freq, Date from, Date to,
			String title) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("insert into reminder(title,message,startDate,endDate,frequency) values(?,?,?,?,?)");
			stmt.setString(1, title);
			stmt.setString(2, message);
			stmt.setTimestamp(3, new Timestamp(from.getTime()));
			stmt.setTimestamp(4, new Timestamp(to.getTime()));
			stmt.setString(5, freq);
			stmt.executeUpdate();
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
	}

	public List<Reminder> getAllReminders() {
		List<Reminder> reminders = new ArrayList<Reminder>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select * from reminder where endDate >= now() order by startDate");
			set = stmt.executeQuery();
			while (set.next()) {
				reminders.add(new Reminder(set.getString("title"), set
						.getString("message"), new java.util.Date(set.getDate(
						"startDate").getTime()), new java.util.Date(set
						.getDate("endDate").getTime()), set
						.getString("frequency")));
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return reminders;
	}
}
