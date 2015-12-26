package com.dbt.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction 
{
	int relatedId;
	int transactionId;
	int amount;
	String paidBy;
	String mode;
	Date datetime;
	String type;
	String description;
	
	
	public Transaction(int relatedId, int transactionId, int amount,
			String paidBy, String mode,String description, Date datetime, String type) {
		super();
		this.relatedId = relatedId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.paidBy = paidBy;
		this.mode = mode;
		this.datetime = datetime;
		this.type = type;
		this.description = description;
	}

	public String getPrintableTime()
	{
		String str = "";
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy KK:mm:ss aa");
		str = format.format(datetime);
		//System.out.println(str);
		return str;
	}
	
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(int relatedId) {
		this.relatedId = relatedId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
