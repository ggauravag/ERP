package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class FeedbackForm extends ActionForm {
	
	int orderId;
	int[] questions;
	
	
	
	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int[] getQuestions() {
		return questions;
	}



	public void setQuestions(int[] questions) {
		this.questions = questions;
	}



	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
		
		if(orderId == 0)
			errors.add("orderError", new ActionMessage("orderID.blankError"));
		if(questions == null || questions.length == 0)
		{
			errors.add("questionError",new ActionMessage("question.blankError"));
		}
		return errors;
	}
}
