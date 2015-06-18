package com.dbt.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order
{
	private Customer customer;
	private List<Product> products;
	private int id;
	private Date datetime;
	private int amount;
	
	public Order(Customer customer, List<Product> products, int id,
			Date datetime, int amount) {
		super();
		this.customer = customer;
		this.products = products;
		this.id = id;
		this.datetime = datetime;
		this.amount = amount;
	}
	
	public Order() {
		super();
		products = new ArrayList<Product>();
		customer = new Customer();
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	

}
