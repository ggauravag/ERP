package com.dbt.data;

public class Merchant
{
	int id;
	String name;
	Address address;
	String tin;
	String mobile;
	String email;
	String logo;
	
	public Merchant(int id,String name, Address address, String tin, String mobile,
			String email, String logo) {
		super();
		this.name = name;
		this.address = address;
		this.tin = tin;
		this.mobile = mobile;
		this.email = email;
		this.logo = logo;
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
