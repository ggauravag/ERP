package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;





import com.dbt.dao.AttendanceDAO;
import com.dbt.forms.ManageEmpForm;

public class ManageEmpAction extends Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result="success";
		int i=0;
		System.out.println("MarkEmployeeAction called");
		ManageEmpForm manageEmpform = (ManageEmpForm) form;
		
		int employee_id = Integer.parseInt(manageEmpform.getEmp_id());
		String tdate = manageEmpform.getTodaydate();
		String halfDay = manageEmpform.getHalfDay();
		System.out.println("ACTION:Employee ID : " + employee_id + ", Date : " + tdate+", HalfDay:"+halfDay);
		
		int hday=0;
		if("Yes".equals(halfDay))
		{
			hday=1;
		}
		
		i = AttendanceDAO.MarkAttendance(employee_id,tdate,hday);
		System.out.println("Value of i value:"+i);
		if(i!=0)
		{
			request.setAttribute("status", "success");
		}
		else
		{
			System.out.println("Else part called of i value:"+i);
			request.setAttribute("status", "failure");
			result = "failure";
			
		}
		
		return mapping.findForward(result);
	}
}
