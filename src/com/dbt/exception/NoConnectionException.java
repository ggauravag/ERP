package com.dbt.exception;

public class NoConnectionException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Connection object is null ! No Connection created in DBConnection class.";
	}
}
