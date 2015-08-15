package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.ExpenditureDAO;
import com.dbt.forms.ExpenditureForm;
import com.dbt.support.Email;

public class ExpenditureAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("ExpenditureAction : Called");
		String result = "failure";
		boolean expStatus;
		ExpenditureForm myform = (ExpenditureForm)form;
		
		try
		{
			int amount = Integer.parseInt(myform.getAmount());
			String mode = myform.getExpMode();
			String description = myform.getDescription();
			String paid = myform.getPaid();
			String dynamic = myform.getDynamic();
			String expType = myform.getExpType();	
			
			
			expStatus = new ExpenditureDAO().addExpenditureDetail(amount, mode, description, paid, dynamic, 
										expType, myform.getReceivedBy(), myform.getempId());
			
			if(expStatus)
			{
				result = "success";
				request.getSession().setAttribute("expStatus", "Success");
			}
			else
				request.getSession().setAttribute("expStatus", "Failure");
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		
		return mapping.findForward(result);
	}
	
}
