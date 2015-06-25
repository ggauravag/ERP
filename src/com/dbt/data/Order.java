package com.dbt.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order
{
	private Customer customer;
	private List<Product> products;
	private List<Order_item> orderitems;
	
	private int id;
	private Date datetime;
	private int amount;
	
	
	int cust_id;
	
	String date;
	String time;
	
	/**
	 * @param id
	 * @param cust_id
	 * @param amount
	 * @param time
	 */
	public Order(int id,int amount,Customer customer,Date datetime)
	{
		this.id = id;
		this.amount = amount;
		this.customer = customer;
		this.datetime = datetime;
	}
	
	public Order(int id, int cust_id, int amount, String time, String date) {
		super();
		this.id = id;
		this.cust_id = cust_id;
		this.amount = amount;
		this.time = time;
		this.date = date;
	}
	/**
	 * @param id
	 * @param amount
	 * @param time
	 */
	public Order(int id, int amount, String date, String time, Customer customer) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.customer = customer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public List<Order_item> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<Order_item> orderitems) {
		this.orderitems = orderitems;
	}
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
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
