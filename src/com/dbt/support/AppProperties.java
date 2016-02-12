package com.dbt.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Properties;

import com.dbt.database.DBConnection;

public class AppProperties {

	static Properties properties = null;

	static {
		String path = AppProperties.class.getClassLoader().getResource("").getPath();
		InputStream inputStream = null;

		try {
			String fullPath = URLDecoder.decode(path, "UTF-8");
			inputStream = new FileInputStream(fullPath + "config.properties");
			properties = new Properties();
			properties.load(inputStream);
			System.out.println("Properties loaded into system ! ");

		} catch (IOException e) {
			e.printStackTrace();
			createFile();
		} finally {

			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static void createFile() {
		OutputStream osStream = null;
		String path = AppProperties.class.getClassLoader().getResource("").getPath();
		Properties properties = null;
		try {
			String fullPath = URLDecoder.decode(path, "UTF-8");
			properties = new Properties();
			osStream = new FileOutputStream(fullPath + "config.properties");

			properties.setProperty("remote." + DBConnection.DATABASE_HOST, "108.161.134.187");
			properties.setProperty("remote." + DBConnection.DATABASE_PORT, "3306");
			properties.setProperty("remote." + DBConnection.DATABASE_USERNAME, "dreambit_root");
			properties.setProperty("remote." + DBConnection.DATABASE_PASSWORD, "#rat123");
			properties.setProperty("remote." + DBConnection.DATABASE_NAME, "dreambit_ramerp");

			properties.setProperty("local." + DBConnection.DATABASE_HOST, "localhost");
			properties.setProperty("local." + DBConnection.DATABASE_PORT, "3306");
			properties.setProperty("local." + DBConnection.DATABASE_USERNAME, "root");
			properties.setProperty("local." + DBConnection.DATABASE_PASSWORD, "rat");
			properties.setProperty("local." + DBConnection.DATABASE_NAME, "ramerp");

			properties.setProperty("local.remote." + DBConnection.DATABASE_HOST, "localhost");
			properties.setProperty("local.remote." + DBConnection.DATABASE_PORT, "3306");
			properties.setProperty("local.remote." + DBConnection.DATABASE_USERNAME, "dreambit_root");
			properties.setProperty("local.remote." + DBConnection.DATABASE_PASSWORD, "#rat123");
			properties.setProperty("local.remote." + DBConnection.DATABASE_NAME, "dreambit_ramerp");

			properties.setProperty(Email.APP_KEY, "grgsow-rdPAqZnMDELMCtA");
			properties.store(osStream, "resources");
			System.out.println("Properties file saved to : " + fullPath + "config.properties");
		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (osStream != null) {
					osStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
	}
}
