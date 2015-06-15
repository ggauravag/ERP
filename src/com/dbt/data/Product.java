package com.dbt.data;

public class Product {
	int id;
	int category;
	String name;
	int quantity;
	int sellPrice;
	int costPrice;

	public Product() {
		this.id = 0;
		this.category = 0;
		this.name = "";
		this.quantity = 0;
		this.sellPrice = 0;
		this.costPrice = 0;
	}

	public Product(int id, int category, String name, int quantity,
			int sellPrice, int costPrice) {
		
		this.id = id;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
		this.costPrice = costPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
}
