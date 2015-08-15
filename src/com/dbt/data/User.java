package com.dbt.data;

import java.sql.Time;

public class User {
	int id;
	String firstName;
	String lastName;
	String email;
	String mobile;
	String password;
	String status;
	String type;
	String photo;
	String logip;
	Time st_time;
	Time end_time;

	public User() {
		firstName = null;
		lastName = null;
		email = null;
		password = null;
		mobile = null;
		id = -1;
		logip = null;
		type = null;
		status = null;
	}
	
	public User(String firstName, String lastName, String email, String mobile) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public User(int id, String firstName, String lastName, String email,
			String mobile, String password, String status, String type,
			String logip,Time st_time, Time end_time) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
		this.type = type;
		this.logip = logip;
		this.st_time = st_time;
		this.end_time = end_time;
	}

	
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Time getSt_time() {
		return st_time;
	}

	public void setSt_time(Time st_time) {
		this.st_time = st_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogip() {
		return logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

}
