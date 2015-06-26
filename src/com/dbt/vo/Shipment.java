package com.dbt.vo;

import java.sql.Timestamp;
import java.util.List;

import com.dbt.data.Product;

public class Shipment 
{
	int id;
	String medium;
	String mediumName;
	String mediumNumber;
	String contact;
	Timestamp time;
	List<Product> items;
	
	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}


	public Shipment() {
		super();
	}
	
	
	public Shipment(int id, String medium, String mediumName,
			String mediumNumber, String contact, Timestamp time) {
		super();
		this.id = id;
		this.medium = medium;
		this.mediumName = mediumName;
		this.mediumNumber = mediumNumber;
		this.contact = contact;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getMediumNumber() {
		return mediumNumber;
	}

	public void setMediumNumber(String mediumNumber) {
		this.mediumNumber = mediumNumber;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	
	
}
