package com.dbt.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dbt.exception.NoConnectionException;
import com.dbt.support.AppProperties;
import com.dbt.support.Email;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnection {

	static ComboPooledDataSource dataSource;
	public static boolean onWebServer = true;
	public static boolean useIP = true;
	public static final String DATABASE_HOST = "database.host";
	public static final String DATABASE_NAME = "database.name";
	public static final String DATABASE_PORT = "database.port";
	public static final String DATABASE_PASSWORD = "database.password";
	public static final String DATABASE_USERNAME = "database.username";
	public static final String LOCATION = "local.remote.";
	
	static {

		try {

			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			String connectionString = "jdbc:mysql://" + AppProperties.getProperty(LOCATION + DATABASE_HOST) + ":"
					+ AppProperties.getProperty(LOCATION + DATABASE_PORT) + "/" + AppProperties.getProperty(LOCATION + DATABASE_NAME)
					+ "?noAccessToProcedureBodies=true";
			String username = AppProperties.getProperty(LOCATION + DATABASE_USERNAME);
			String password = AppProperties.getProperty(LOCATION + DATABASE_PASSWORD);

			
			System.out.println("Connect URL : "+connectionString+", Username : "+username+", Password : "+password);
			
			/*
			 * if (onWebServer) { if (useIP) { connectionString =
			 * "jdbc:mysql://108.161.134.187:3306/dreambit_ramerp?noAccessToProcedureBodies=true";
			 * } else { connectionString =
			 * "jdbc:mysql://localhost:3306/dreambit_ramerp?noAccessToProcedureBodies=true";
			 * }
			 * 
			 * username = "dreambit_root"; password = "#rat123"; } else {
			 * connectionString = "jdbc:mysql://localhost:3306/ramerp"; username
			 * = "root"; password = "rat"; }
			 */

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

	public static void closeResource(Connection con, Statement stmt, ResultSet res) {

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
				//System.out.println("Connection Closed");
			} catch (Exception e) {
				Email.sendExceptionReport(e);
			}
		}
	}

	public static Connection getConnection() throws NoConnectionException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			//System.out.println("Connection Created");
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			if (con == null)
				throw new NoConnectionException();
		}
		return con;
	}
}
