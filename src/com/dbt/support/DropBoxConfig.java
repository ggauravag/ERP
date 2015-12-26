package com.dbt.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class DropBoxConfig {

	public static final String ACCESS_TOKEN = "cJtvIYbzqLYAAAAAAAAAFT_KLNfvoHJZDfq2UQovHZy1ZUq5GHRyNRTzbuFVF2jc";
	public static final String APP_KEY = "2nl25hytmesw3jh";
	public static final String SECRET = "bm14tma6ayno981";
	DbxClient dbxClient;

	public DbxClient authDropbox(String dropBoxAppKey, String dropBoxAppSecret)
			throws IOException, DbxException {
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig(
				"JavaDropbox/1.0", Locale.getDefault().toString());
		/*
		 * DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(
		 * dbxRequestConfig, dbxAppInfo); String authorizeUrl =
		 * dbxWebAuthNoRedirect.start();
		 * System.out.println("1. Authorize: Go to URL and click Allow : " +
		 * authorizeUrl); System.out
		 * .println("2. Auth Code: Copy authorization code and input here ");
		 * String dropboxAuthCode = new BufferedReader(new InputStreamReader(
		 * System.in)).readLine().trim(); DbxAuthFinish authFinish =
		 * dbxWebAuthNoRedirect.finish(dropboxAuthCode); String authAccessToken
		 * = authFinish.accessToken;
		 */
		dbxClient = new DbxClient(dbxRequestConfig, ACCESS_TOKEN);
		System.out.println("Dropbox Account Name: "
				+ dbxClient.getAccountInfo().displayName);

		return dbxClient;
	}

	public long getDropboxSize() throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
	}

	public void uploadToDropbox(String fileName) throws DbxException,
			IOException {
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + fileName,
					DbxWriteMode.add(), inputFile.length(), fis);
			//System.out.println("Temporaray Direct URL : "+dbxClient.createTemporaryDirectUrl("/" + fileName));
			String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
			System.out.println("Uploaded: " + uploadedFile.name + " URL: "
					+ sharedUrl);
		} finally {
			fis.close();
		}
	}
	
	public boolean deleteFromDropBox(String fileName) throws DbxException
	{
		boolean result = true;
		try
		{
			dbxClient.delete("/"+fileName);
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}
	
	public String uploadToDropbox(InputStream fis,long size,String fileName) throws DbxException,
	IOException 
	{
		String sharedUrl = "";
		DbxEntry.File uploadedFile = null;
		try {
			uploadedFile = dbxClient.uploadFile("/" + fileName,
					DbxWriteMode.add(), size, fis);
			//System.out.println("Temporary Direct URL : "+dbxClient.createTemporaryDirectUrl("/" + fileName));
			sharedUrl = dbxClient.createShareableUrl("/" + fileName);
			
			System.out.println("Uploaded: " + uploadedFile.name + " to URL: "
					+ sharedUrl);
		} finally {
			fis.close();
		}
		return sharedUrl.replace("www", "dl") + ";" + uploadedFile.name;
	}
	
	public void listDropboxFolders(String folderPath) throws DbxException {
		DbxEntry.WithChildren listing = dbxClient
				.getMetadataWithChildren(folderPath);
		
		System.out.println("Files List:");
		for (DbxEntry child : listing.children) 
		{		
			System.out.println("	" + child.name + ", Path : " + child.path);
		}
	}

	public void downloadFromDropbox(String fileName) throws DbxException,
			IOException {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		try {
			DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName,
					null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}
	
	public DropBoxConfig()
	{
		try {
			authDropbox(APP_KEY, SECRET);
		} catch (IOException | DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DropBoxConfig dropbox = new DropBoxConfig();
		try {
			dropbox.authDropbox(APP_KEY, SECRET);
			System.out.println("Dropbox Size: " + dropbox.getDropboxSize()
					+ " GB");
			//dropbox.uploadToDropbox("email.txt");
			dropbox.listDropboxFolders("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
