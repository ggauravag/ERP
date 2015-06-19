package com.dbt.data;

public class Capital {

	private int id;
	private int amount;
	private int rate;
	private String lender;
	
	
	public Capital(int id, int amount, int rate, String lender) {
		super();
		this.id = id;
		this.amount = amount;
		this.rate = rate;
		this.lender = lender;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public String getLender() {
		return lender;
	}


	public void setLender(String lender) {
		this.lender = lender;
	}
	
	
	
}
