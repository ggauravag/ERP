package com.dbt.data;

public class Address{
	String houseNo;
	String Line1;
	String Line2;
	String city;
    String state;
    String zip;
        
	public Address() {
		super();
	}
	
	
	public Address(String houseNo, String Line1, String Line2,
			String city, String state, String zip) {
		super();
		this.houseNo = houseNo;
		this.Line1 = Line1;
		this.Line2 = Line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getLine1() {
		return Line1;
	}
	public void setLine1(String Line1) {
		this.Line1 = Line1;
	}
	public String getLine2() {
		return Line2;
	}
	public void setLine2(String Line2) {
		this.Line2 = Line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
