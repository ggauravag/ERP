package com.dbt.data;

public class Loan {

	private int id;
	private int amount;
	private int tenure;
	private int installement;
	private int interest;
	
	public Loan(int id, int amount, int tenure, int installement, int interest) {
		super();
		this.id = id;
		this.amount = amount;
		this.tenure = tenure;
		this.installement = installement;
		this.interest = interest;
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
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getInstallement() {
		return installement;
	}
	public void setInstallement(int installement) {
		this.installement = installement;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
}
