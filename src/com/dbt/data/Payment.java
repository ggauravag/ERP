package com.dbt.data;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dbt.support.NumberToWord;

public class Payment {
	int id;
	int receiptId;
	String mode;
	String paidBy;
	Date datetime;
	int amount;
	int orderId;
	String type;
	String description;

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public String getAmountInWords() {
		return new NumberToWord().convertNumberToWords(amount);
	}
	
	public String getPrintableTime()
	{
		String date = "";
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy | hh:mm aa");
		date = format.format(datetime);
		return date;
	}

	static public String customFormat(String pattern, int value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format((double) value);
		return output;
	}

	public Payment(int id, String mode, String paidBy, Date datetime,
			int amount, int orderId, String type) {
		super();
		this.id = id;
		this.paidBy = paidBy;
		this.datetime = datetime;
		this.amount = amount;
		this.orderId = orderId;
		this.type = type;
		String[] val = mode.split(";");
		if (val.length >= 2) {
			this.description = val[1];
		} else
			this.description = "";

		this.mode = mode.split(";")[0];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public String getPrintableAmount() {
		return customFormat("###,###,###.###", amount);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
