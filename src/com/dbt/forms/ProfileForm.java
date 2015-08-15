package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ProfileForm extends ActionForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String action;
    private String Fname;
    private String Lname;
    private int userid;
    private String mobile;
    private String password;
    private String confPass;
    
    
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String Fname) {
		this.Fname = Fname;
	}
	
	public String getLname() {
		return Lname;
	}
	public void setLname(String Lname) {
		this.Lname = Lname;
	}
	@Override
    public ActionErrors validate(ActionMapping mapping,
    		HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	System.out.println("User Fname : " + Fname + ", lname : " + Lname+" ,uid: "+userid+", action :"+action+", mobile:"+mobile+",Pass:"+password+",ConfPass:"+confPass);
        
    	 Fname=request.getParameter("Fname");
    	 Lname = request.getParameter("Lname");
    	
    	 
    	 ActionErrors errors = new ActionErrors();
       
		if("Name".equals(getAction()))
		{
		    if("".equals(Fname) || Fname.trim().length()==0 || Fname == null)
		    {
		    	errors.add("fnameError", new ActionMessage("name.blankError"));
		    }
		    if("".equals(Lname) || Lname.trim().length()==0 || Lname == null)
		    {
		    	errors.add("lnameError", new ActionMessage("name.blankError"));
		    }
		}
		else if("Contact".equals(getAction()))
		{
			if("".equals(mobile) || mobile.trim().length()==0 || mobile == null)
			{
				errors.add("mobileError",new ActionMessage("mobile.blankError"));
			}
		}
		else if("Pass".equals(getAction()))
		{
			if("".equals(password) || password.trim().length()==0 || password == null)
			{
				errors.add("passError",new ActionMessage("password.blankError"));
			}
			if("".equals(confPass) || confPass.trim().length()==0 || confPass == null)
			{
				errors.add("confpassError",new ActionMessage("password.blankError"));
			}
			if(!password.equals(confPass))
			{
				errors.add("passError",new ActionMessage("password.sameError"));
				errors.add("confpassError",new ActionMessage("password.sameError"));
			}
		}
		
		    	return errors;
   }
}
