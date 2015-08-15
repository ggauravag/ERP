package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.LoginDAO;
import com.dbt.dao.ProfileDAO;
import com.dbt.data.User;
import com.dbt.forms.ProfileForm;
import com.dbt.support.Utils;

public class ProfileAction extends Action
{
   @Override
public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	   
	   String result="failure";
	  // System.out.println("Profile Action called");
	   ProfileForm profileForm = (ProfileForm) form;
	   
	   if("Name".equals(profileForm.getAction()))
	   {
		   String fname = profileForm.getFname();
		   String lname = profileForm.getLname();
		   int uid = profileForm.getUserid();
		   int i = new ProfileDAO().updateName(fname,lname,uid);
		   User oldUser = (User)request.getSession().getAttribute("user");
		   User user = new LoginDAO().login(oldUser.getEmail(), oldUser.getPassword());
		   request.getSession().setAttribute("user", user);
		   if(i!=0)
		   {
			   request.setAttribute("status", "done");
			   result="success";
		   }
		   else
		   {
			   request.setAttribute("status", "failure");
		   }
	   }
	   else if("Contact".equals(profileForm.getAction()))
	   {
		   String mobile = profileForm.getMobile().replace("-","");
		   int uid = profileForm.getUserid();
		   int i = new ProfileDAO().updateMobile(mobile,uid);
		   User oldUser = (User)request.getSession().getAttribute("user");
		   User user = new LoginDAO().login(oldUser.getEmail(), oldUser.getPassword());
		   request.getSession().setAttribute("user", user);
		   if(i!=0)
		   {
			   request.setAttribute("status", "done");
			   result="success";
		   }
		   else
		   {
			   request.setAttribute("status", "failure");
		   }
	   }
	   else if("Pass".equals(profileForm.getAction()))
	   {
		   String pass = profileForm.getPassword();
		   int uid = profileForm.getUserid();
		   String encpass = Utils.getMD5(pass);
		   int i = new ProfileDAO().updatePass(encpass,uid);
		   if(i!=0)
		   {
			   request.setAttribute("status", "done");
			   result="login";   
		   }
		   else
		   {
			   request.setAttribute("status", "failure");
		   }
	   }
	   else
	   {
		   request.setAttribute("status", "failure");
	   }
	   
	   
	   
	   return mapping.findForward(result);
}
}
