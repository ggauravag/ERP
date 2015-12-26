package com.dbt.data;

import java.util.Date;

public class Reminder {
	
	String title,message;
	Date startDate,endDate;
	String frequency;
	
	public Reminder(String title, String message, Date startDate, Date endDate,
			String frequency) {
		super();
		this.title = title;
		this.message = message;
		this.startDate = startDate;
		this.endDate = endDate;
		this.frequency = frequency;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	
	

}
