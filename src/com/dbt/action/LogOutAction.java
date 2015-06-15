package com.dbt.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogOutAction extends Action 
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String result = "error";
		if(session != null && session.getAttribute("user") != null)
		{
			session.removeAttribute("user");
			session.invalidate();
			Cookie cookie = new Cookie("auth_token", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			System.out.println("Session Cleared!");
			result = "success";
		}
		else
		{
			System.out.println("No Session");
		}
		return mapping.findForward(result);
	}

}
