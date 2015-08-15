package com.dbt.data;

import java.util.Date;

public class Purchase {
	int id;
	Date date;
	String merchantName;
	int orderAmount;
	int paidAmount;
	
	public Purchase(int id, Date date, String merchantName, int orderAmount,
			int paidAmount) {
		super();
		this.id = id;
		this.date = date;
		this.merchantName = merchantName;
		this.orderAmount = orderAmount;
		this.paidAmount = paidAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	
	
}
