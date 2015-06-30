package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.ComplaintDAO;
import com.dbt.data.User;
import com.dbt.forms.ComplaintForm;

public class RequestComplaintAction extends Action
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result="success";
		System.out.println("RequestComplaintAction class called.");
		ComplaintForm complaintform = (ComplaintForm) form;
		
		int oid = 0,comp_id=0,uid=0;
		oid = complaintform.getOrder_id();
		String comment = complaintform.getComment();
		User user = (User) request.getSession().getAttribute("user");
		uid = user.getId();
		System.out.println("RequestComplaintAction: "+oid+":"+comment+":"+uid);
		
		if(comment!=null && comment!="" && uid!=0 && oid!=0)
		{
		  comp_id =	ComplaintDAO.insertComplaint(oid, comment, uid);
		  
		  if(comp_id!=0)
		  {
			  request.setAttribute("status", "success");
			  request.setAttribute("complaintID", comp_id);
		  }
		  else
		  {
			  result="failure";
			  request.setAttribute("status", "failure");
		  }
		  
		}
		else
		{
			result="failure";
		request.setAttribute("status", "failure");
		}
		return mapping.findForward(result);
	}
}
