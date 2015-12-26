package com.dbt.data;

public class Question {

	int id;
	String text;
	String type;
	int feedbackID;
	String response;

	public Question(int id, String text, String type, int feedbackID) {
		super();
		this.id = id;
		this.text = text;
		this.type = type;
		this.feedbackID = feedbackID;
	}

	public Question(int id, String text, String type, int feedbackID,
			String response) {
		super();
		this.id = id;
		this.text = text;
		this.type = type;
		this.feedbackID = feedbackID;
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
