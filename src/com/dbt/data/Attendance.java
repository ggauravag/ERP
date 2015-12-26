package com.dbt.data;

import java.sql.Date;

public class Attendance {

	int empId;
	Date date;
	int halfDay;
	
	
	public Attendance(int empId, Date date, int halfDay) {
		super();
		this.empId = empId;
		this.date = date;
		this.halfDay = halfDay;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHalfDay() {
		return halfDay;
	}
	public void setHalfDay(int halfDay) {
		this.halfDay = halfDay;
	}
	
}
