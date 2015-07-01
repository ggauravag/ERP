package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.ExpenditureDAO;
import com.dbt.forms.ExpenditureForm;

public class ExpenditureAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("ExpenditureAction : Called");
		String result = "failure";
		int txId = 0;
		int expId = 0;
		boolean status;
		ExpenditureForm myform = (ExpenditureForm)form;
		
		try
		{
			int amount = Integer.parseInt(myform.getAmount());
			String mode = myform.getExpMode();
			String description = myform.getDescription();
			String paid = myform.getPaid();
			String dynamic = myform.getDynamic();
			String expType = myform.getExpType();	
				
			txId = ExpenditureDAO.addTransaction(amount, mode, description, paid);
			
			if(txId != 0)
			{
				expId = ExpenditureDAO.addExpenditure(txId);
				
				if(expId != 0)
				{
					if(expType.equals("salary"))
					{
						String receivedBy = myform.getReceivedBy();
						int empId = Integer.parseInt(myform.getempId());
						status = ExpenditureDAO.addSalaryExpenditure(expId, empId, receivedBy);
						
						if(status)
						{
							result = "success";
							System.out.print("ExpenditureAction : Salary Expenditure added. ");
						}
					}
					else if(expType.equals("daily"))
					{
						status = ExpenditureDAO.addDailyExpenditure(expId, dynamic);
						
						if(status)
						{
							result = "success";
							System.out.print("ExpenditureAction : Daily Expenditure added. ");
						}
					}
					else if(expType.equals("loan"))
					{
						status = ExpenditureDAO.addLoanExpenditure(expId, Integer.parseInt(dynamic));
						
						if(status)
						{
							result = "success";
							System.out.print("ExpenditureAction : Loan Expenditure added. ");
						}
					}
					else if(expType.equals("interest"))
					{
						status = ExpenditureDAO.addInterestExpenditure(expId, Integer.parseInt(dynamic));
						
						if(status)
						{
							result = "success";
							System.out.print("ExpenditureAction : Interest Expenditure added. ");
						}
					}
				}
				else
				{
					System.out.println("ExpenditureAction : Expenditure not added, Cant Proceed");
				}
			}
			else
			{
				System.out.println("ExpenditureAction : Transaction ID = 0, Cant Proceed");
			}
			
			if(result.equals("success"))
			{
				request.getSession().setAttribute("expStatus", "Success");
			}
			else
				request.getSession().setAttribute("expStatus", "Failure");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mapping.findForward(result);
	}
	
}
