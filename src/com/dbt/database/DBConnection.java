package com.dbt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class DBConnection {
	static String connectionString;
	static String username;
	static String password;
	public static boolean onWebServer = false;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}
	}

	public static void closeResource(Connection con, PreparedStatement stmt,
			ResultSet res) {

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				Email.sendExceptionReport(e);
			}
		}

		if (res != null) {
			try {
				res.close();
			} catch (Exception e) {

				Email.sendExceptionReport(e);
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {

				Email.sendExceptionReport(e);
			}
		}
	}

	public static Connection getConnection() throws NoConnectionException {
		Connection con = null;
		try {

			if (onWebServer) {
				connectionString = "jdbc:mysql://localhost:3306/dreambit_ramerp?noAccessToProcedureBodies=true";
				username = "dreambit_root";
				password = "#rat123";
			} else {
				connectionString = "jdbc:mysql://localhost:3306/ramerp";
				username = "root";
				password = "rat";
			}

			/*
			 * Logger logger =
			 * Logger.getLogger("com.dbt.database.DDConnection");
			 * logger.addHandler(new JDBCLogHandler());
			 * logger.setLevel(Level.ALL); logger.info("Connection Created");
			 */

			con = DriverManager.getConnection(connectionString, username,
					password);

		} catch (SQLException e) {
			Email.sendExceptionReport(e);
		} finally {
			if (con == null)
				throw new NoConnectionException();
		}
		return con;
	}

}
