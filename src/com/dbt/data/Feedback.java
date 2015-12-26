package com.dbt.data;

import java.util.Date;
import java.util.List;

public class Feedback {
	
	int id;
	int orderId;
	Date generateDate,submitDate;
	List<Question> questions;
	
	public Feedback(int id, int orderId, Date generateDate, Date submitDate,
			List<Question> questions) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.generateDate = generateDate;
		this.submitDate = submitDate;
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

}
