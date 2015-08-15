package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class ComplaintForm extends ActionForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order_id;
	private String comment;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
		
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		System.out.println("Order ID : " + order_id + ", comment : " + comment);
		ActionErrors errors = new ActionErrors();
		boolean isCommentEmpty = false;
		//boolean isOrderIDEmpty = false;
		
		if(comment == null || comment.trim().length() == 0)
		{
			isCommentEmpty = true;
		}
		
		if(isCommentEmpty)
		{
			errors.add("CommentError",new ActionMessage("Comment.blankError"));
		}
		
		return errors;
		
	}
}
