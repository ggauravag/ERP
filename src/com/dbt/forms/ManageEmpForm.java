package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ManageEmpForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String emp_id;
	private String todaydate;
	private String halfDay;
	
	
	
	
	
	
	public String getHalfDay() {
		return halfDay;
	}




	public void setHalfDay(String halfDay) {
		this.halfDay = halfDay;
	}




	public String getEmp_id() {
		return emp_id;
	}




	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}




	public String getTodaydate() {
		return todaydate;
	}




	public void setTodaydate(String todaydate) {
		this.todaydate = todaydate;
	}




	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		emp_id = request.getParameter("empid");
		todaydate = request.getParameter("curdate");
		System.out.println("Employee ID : " + emp_id + ", Date : " + todaydate+", HalfDay:"+halfDay);
		ActionErrors errors = new ActionErrors();
		
		if("".equals(emp_id) || emp_id.trim().length()==0)
		{
			errors.add("EmployeeError",new ActionMessage("Employee.BlankError"));			
		}
		if("".equals(todaydate) || todaydate.trim().length()==0)
		{
			errors.add("DateError", new ActionMessage("Date.BlankError"));
		}
		return errors;
	}
}
