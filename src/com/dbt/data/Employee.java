package com.dbt.data;

import java.sql.Date;

public class Employee {
	int employee_id;
	String role;
	Date date_of_join;
	int salary;
	String status;
	Date date_of_leave;
	User user;
	Address address;

	public Employee(int employee_id, Date date_of_join, int salary, User user) {
		super();
		this.date_of_join = date_of_join;
		this.salary = salary;
		this.user = user;
		this.employee_id = employee_id;
	}

	public Employee(int employee_id, String role, Date date_of_join,
			int salary, String status, Date date_of_leave) {
		super();
		this.employee_id = employee_id;
		this.role = role;
		this.date_of_join = date_of_join;
		this.salary = salary;
		this.status = status;
		this.date_of_leave = date_of_leave;
	}

	
	public Employee(int employee_id,Date date_of_join, String role, int salary, User user,Address address) {
        super();
        this.date_of_join = date_of_join;
        this.salary = salary;
        this.role = role;
        this.user = user;
        this.employee_id=employee_id;
        this.address = address;
    }   
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDate_of_join() {
		return date_of_join;
	}

	public void setDate_of_join(Date date_of_join) {
		this.date_of_join = date_of_join;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate_of_leave() {
		return date_of_leave;
	}

	public void setDate_of_leave(Date date_of_leave) {
		this.date_of_leave = date_of_leave;
	}

}
