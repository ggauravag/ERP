package com.dbt.data;

public class Order_item {

	int order_id;
	int product_id;
	int quantity;
	int amount;
	
	public Order_item() {
		// TODO Auto-generated constructor stub
		this.order_id = 0;
		this.product_id = 0;
		this.quantity = 0;
		this.amount = 0;
	}

	public Order_item(int quantity, int amount) {
		
		this.order_id = 0;
		this.product_id = 0;
		this.quantity = quantity;
		this.amount = amount;
		
	}
	
	/**
	 * @param order_id
	 * @param product_id
	 * @param quantity
	 * @param amount
	 */
	public Order_item(int order_id, int product_id, int quantity, int amount) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.amount = amount;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
