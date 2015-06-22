package com.dbt.data;

public class Complaint 
{

	int id;
	int order_id;
	String status;
	String register_date; 
	
	public Complaint()
	{
		super();
	}

	/**
	 * @param id
	 * @param order_id
	 * @param status
	 * @param register_date
	 */
	public Complaint(int id, int order_id, String status, String register_date) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.status = status;
		this.register_date = register_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	
	
}
