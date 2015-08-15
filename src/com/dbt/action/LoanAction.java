package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.FundDAO;
import com.dbt.forms.LoanForm;
import com.dbt.support.Email;

public class LoanAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("LoanAction : Called");
		String result = "failure";
		LoanForm myform = (LoanForm)form;
		
		try{
			int loanAmount = myform.getInputAmount();
			int loanTenure = myform.getInputTenure();
			int loanInstallment = myform.getInputInstallment();
			double loanInterest = myform.getInputInterest();
			
			boolean state = new FundDAO().addLoan(loanAmount, loanTenure, loanInstallment, loanInterest);
			
			if(state)
			{
				result = "success";
				request.setAttribute("loanStatus", "Success");
			}
			else
				request.setAttribute("loanStatus", "Failure");
			
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		
		return mapping.findForward(result);
	}

}
