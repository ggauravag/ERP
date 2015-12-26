package com.dbt.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnection {
	static String connectionString;
	static String username;
	static String password;
	static ComboPooledDataSource dataSource;
	public static boolean onWebServer = false;
	public static boolean useIP = true;

	static 
	{

		try {

			// Class.forName("com.mysql.jdbc.Driver");
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");

			if (onWebServer) {
				if (useIP) {
					connectionString = "jdbc:mysql://108.161.134.187:3306/dreambit_ramerp?noAccessToProcedureBodies=true";
				} else {
					connectionString = "jdbc:mysql://localhost:3306/dreambit_ramerp?noAccessToProcedureBodies=true";
				}

				username = "dreambit_root";
				password = "#rat123";
			} else {
				connectionString = "jdbc:mysql://localhost:3306/ramerp";
				username = "root";
				password = "rat";
			}

			dataSource.setJdbcUrl(connectionString);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setAcquireIncrement(1);
			dataSource.setAcquireRetryAttempts(1000);
			dataSource.setAcquireRetryDelay(3);
			dataSource.setMinPoolSize(1);
			dataSource.setMaxPoolSize(15);
			dataSource.setInitialPoolSize(1);
			dataSource.setMaxIdleTime(3);
			dataSource.setIdleConnectionTestPeriod(3);
			dataSource.setPreferredTestQuery("select 1");

		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}
	}

	public static void closeResource(Connection con, Statement stmt,
			ResultSet res) {

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

		if (con != null) {
			try {
				con.close();
				System.out.println("Connection Closed");
			} catch (Exception e) {
				Email.sendExceptionReport(e);
			}
		}
	}

	public static Connection getConnection() throws NoConnectionException {
		Connection con = null;
		try {
			// System.out.println("Getting Connection .....!");
			con = dataSource.getConnection();
			System.out.println("Connection Created");
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			if (con == null)
				throw new NoConnectionException();
		}
		return con;
	}
}
