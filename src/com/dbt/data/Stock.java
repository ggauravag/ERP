package com.dbt.data;

import java.util.List;

public class Stock {
	
	List<Product> products;
	int category;
	int amount;
	
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Stock(List<Product> products, int category) {
	
		this.products = products;
		this.category = category;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
