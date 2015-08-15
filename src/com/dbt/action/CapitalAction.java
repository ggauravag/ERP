package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.FundDAO;
import com.dbt.forms.CapitalForm;
import com.dbt.support.Email;

public class CapitalAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("CapitalAction : Called");
		String result = "failure";
		
		try{
			CapitalForm myform = (CapitalForm)form;
			
			int amount = myform.getInputAmount();
			String lender = myform.getInputLender();
			double interest = myform.getInputRate();
			
			boolean state = FundDAO.addCapital(amount, interest, lender);
			
			if(state)
			{
				result = "success";
				request.setAttribute("capitalStatus", "Success");
			}
			else
				request.setAttribute("capitalStatus", "Success");
			
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		
		return mapping.findForward(result);
	}
	
}
