package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.FundDAO;
import com.dbt.forms.LoanForm;

public class LoanAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("LoanAction : Called");
		String result = "failure";
		LoanForm myform = (LoanForm)form;
		
		try{
			int loanAmount = Integer.parseInt(myform.getLoanAmount());
			int loanTenure = Integer.parseInt(myform.getLoanTenure());
			int loanInstallment = Integer.parseInt(myform.getLoanInstallment());
			int loanInterest = Integer.parseInt(myform.getLoanRate());
			
			boolean state = FundDAO.addLoan(loanAmount, loanTenure, loanInstallment, loanInterest);
			
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
			e.printStackTrace();
		}
		
		return mapping.findForward(result);
	}

}
