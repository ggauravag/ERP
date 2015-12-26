package com.dbt.data;

public class Expenditure 
{
	String type;
	int id;
	Transaction transaction;
	String details;
	
	public Expenditure(String type, int id, Transaction transaction,
			String details) {
		super();
		this.type = type;
		this.id = id;
		this.transaction = transaction;
		this.details = details;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
