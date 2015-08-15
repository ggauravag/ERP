/**
 * 
 */
package com.dbt.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;


public class ManageEmployeeForm extends ActionForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Empname;
	private String EmployeeID;
	private String EmployeeEmail;
	private String EmployeeRole;
	private String EmployeeDoj;
	private String EmployeeMobile;
	private String EmployeeSal;
	private String EmployeeDueSal;
	private String EmployeeHouse;
	private String Employeeline1;
	private String Employeeline2;
	private String EmployeeCity;
	private String EmployeeState;
	private String EmployeeZip;
	private int numIDProof;
	private FormFile proof1;
	private FormFile proof2;
	private FormFile proof3;
	private FormFile proof4;
	
	
	public String getEmployeeDueSal() {
		return EmployeeDueSal;
	}
	public void setEmployeeDueSal(String employeeDueSal) {
		EmployeeDueSal = employeeDueSal;
	}
	public String getEmpname() {
		return Empname;
	}
	public void setEmpname(String Empname) {
		this.Empname = Empname;
	}
	public String getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(String EmployeeID) {
		this.EmployeeID = EmployeeID;
	}
	public String getEmployeeEmail() {
		return EmployeeEmail;
	}
    public void setEmployeeEmail(String EmployeeEmail) {
		this.EmployeeEmail = EmployeeEmail;
	}
	public String getEmployeeRole() {
		return EmployeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		EmployeeRole = employeeRole;
	}
	public String getEmployeeDoj() {
		return EmployeeDoj;
	}
	public void setEmployeeDoj(String employeeDoj) {
		EmployeeDoj = employeeDoj;
	}
	public String getEmployeeMobile() {
		return EmployeeMobile;
	}
	public void setEmployeeMobile(String employeeMobile) {
		EmployeeMobile = employeeMobile;
	}
	public String getEmployeeSal() {
		return EmployeeSal;
	}
	public void setEmployeeSal(String employeeSal) {
		EmployeeSal = employeeSal;
	}
	public String getEmployeeHouse() {
		return EmployeeHouse;
	}
	public void setEmployeeHouse(String employeeHouse) {
		EmployeeHouse = employeeHouse;
	}
	public String getEmployeeline1() {
		return Employeeline1;
	}
	public void setEmployeeline1(String employeeline1) {
		Employeeline1 = employeeline1;
	}
	public String getEmployeeline2() {
		return Employeeline2;
	}
	public void setEmployeeline2(String employeeline2) {
		Employeeline2 = employeeline2;
	}
	public String getEmployeeCity() {
		return EmployeeCity;
	}
	public void setEmployeeCity(String employeeCity) {
		EmployeeCity = employeeCity;
	}
	public String getEmployeeState() {
		return EmployeeState;
	}
	public void setEmployeeState(String EmployeeState) {
		this.EmployeeState = EmployeeState;
	}
	public String getEmployeeZip() {
		return EmployeeZip;
	}
	public void setEmployeeZip(String EmployeeZip) {
		this.EmployeeZip = EmployeeZip;
	}
	public int getNumIDProof() {
		return numIDProof;
	}
	public void setNumIDProof(int numIDProof) {
		this.numIDProof = numIDProof;
	}
	public FormFile getProof1() {
		return proof1;
	}
	public void setProof1(FormFile proof1) {
		this.proof1 = proof1;
	}
	public FormFile getProof2() {
		return proof2;
	}
	public void setProof2(FormFile proof2) {
		this.proof2 = proof2;
	}
	public FormFile getProof3() {
		return proof3;
	}
	public void setProof3(FormFile proof3) {
		this.proof3 = proof3;
	}

	public FormFile getProof4() {
		return proof4;
	}

	public void setProof4(FormFile proof4) {
		this.proof4 = proof4;
	}




	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		ActionErrors errors = new ActionErrors();
		
	
	
	    Empname = request.getParameter("Empname");
	    EmployeeMobile = request.getParameter("EmployeeMobile");
	    EmployeeRole = request.getParameter("EmployeeRole");
	    EmployeeSal = request.getParameter("EmployeeSal");
	    EmployeeEmail = request.getParameter("EmployeeEmail");
	    EmployeeCity = request.getParameter("EmployeeCity");
	    EmployeeHouse = request.getParameter("EmployeeHouse");
	    Employeeline1 = request.getParameter("Employeeline1");
	    Employeeline2 = request.getParameter("Employeeline2");
	    EmployeeState = request.getParameter("EmployeeState");
	    EmployeeID = request.getParameter("EmployeeID");
	    EmployeeZip = request.getParameter("EmployeeZip");
	    EmployeeDoj = request.getParameter("EmployeeDoj");
	    //EmployeeDueSal = request.getParameter("EmployeeDueSal");
	    
	    
	    
	    System.out.println("Name:"+Empname+",Mobile:"+EmployeeMobile+",Email:"+EmployeeEmail+",City:"+EmployeeCity+",House:"+EmployeeHouse+",line1:"+Employeeline1+",line2:"+Employeeline2+",State:"+EmployeeState+",Zip:"+EmployeeZip+",Salary:"+EmployeeSal+",ID:"+EmployeeID+",DOJ:"+EmployeeDoj+",numID:"+numIDProof+",Role:"+EmployeeRole+",EmployeeDueSalary:"+EmployeeDueSal);	
		   
	    
	    
	    
		
	    if("".equals(Empname) || Empname.trim().length()==0 || Empname == null)
	    	errors.add("nameError", new ActionMessage("name.blankError"));
	    
	    if (EmployeeMobile == null || EmployeeMobile.trim().length() == 0 || "".equals(EmployeeMobile))
			errors.add("mobileError", new ActionMessage("mobile.blankError"));
		
	    if (EmployeeRole == null || EmployeeRole.trim().length() == 0 || "".equals(EmployeeRole))
			errors.add("roleError", new ActionMessage("role.blankError"));
	    
		if (EmployeeSal == null || EmployeeSal.trim().length() == 0 || "".equals(EmployeeSal))
			errors.add("salaryError", new ActionMessage("salary.invalidError"));
	   
		
		if ("".equals(EmployeeEmail) || EmployeeEmail == null || EmployeeEmail.trim().length() == 0)
			errors.add("emailError", new ActionMessage("email.blankError"));
		
		if ("".equals(EmployeeCity) || EmployeeCity == null || EmployeeCity.trim().length() == 0)
			errors.add("cityError", new ActionMessage("city.blankError"));
		
		if ("".equals(EmployeeHouse) || EmployeeHouse == null || EmployeeHouse.trim().length() == 0)
			errors.add("houseError", new ActionMessage("house.blankError"));
		
		if ("".equals(Employeeline1) || Employeeline1 == null || Employeeline1.trim().length() == 0)
			errors.add("line2Error", new ActionMessage("line1.blankError"));
		
		if ("".equals(Employeeline2) || Employeeline2 == null || Employeeline2.trim().length() == 0)
			errors.add("line2Error", new ActionMessage("line2.blankError"));
		
		if ("".equals(EmployeeState) || EmployeeState == null || EmployeeState.trim().length() == 0)
			errors.add("stateError", new ActionMessage("state.blankError"));
		
		if ("".equals(EmployeeID) || EmployeeID == null || EmployeeID.trim().length() == 0)
			errors.add("empidError", new ActionMessage("id.blankError"));
		
		if ("".equals(EmployeeZip) || EmployeeZip == null || EmployeeZip.trim().length() == 0)
			errors.add("zipError", new ActionMessage("zip.blankError"));
		
		if ("".equals(EmployeeDoj) || EmployeeDoj == null || EmployeeDoj.trim().length() == 0)
			errors.add("dojError", new ActionMessage("doj.blankError"));
		
		if ("".equals(numIDProof) ||  numIDProof <= 0)
			errors.add("numIDError", new ActionMessage("numID.blankError"));
		
		if (proof1.getFileSize()==0)
			errors.add("proof1Error", new ActionMessage("error.proof1.invalid"));
		else 
		{
			System.out.println("Picture Uploaded");
			String mimeType = proof1.getContentType();
			if (!mimeType.startsWith("image/") && proof1.getFileSize() <= 0) 
			{
				errors.add("proof1Error", new ActionMessage("error.proof1.invalid"));
			}
			System.out.println("Size : " + proof1.getFileSize() + "," + proof1.getContentType());
			System.out.println("Name : " + proof1.getFileName());
		}
		
		if(proof2.getFileSize()==0)
		{
			
		}
		else
		{
			System.out.println("Proof 2");
			String mimeType = proof2.getContentType();
			if (!mimeType.startsWith("image/") && proof2.getFileSize() <= 0) 
			{
				errors.add("proof2Error", new ActionMessage("error.proof2.invalid"));
				}
			System.out.println("Size : " + proof2.getFileSize() + "," + proof2.getContentType());
			System.out.println("Name : " + proof2.getFileName());
		}
		
		if(proof3.getFileSize()==0){
			
		}else
		{
			System.out.println("Proof 3");
			String mimeType = proof3.getContentType();
			if (!mimeType.startsWith("image/") && proof3.getFileSize() <= 0) 
			{
				errors.add("proof3Error", new ActionMessage("error.proof3.invalid"));
				}
			System.out.println("Size : " + proof3.getFileSize() + "," + proof3.getContentType());
			System.out.println("Name : " + proof3.getFileName());
		}
		
		if(proof4.getFileSize()==0){
			
		}else
		{
			System.out.println("Proof 4");
			String mimeType = proof4.getContentType();
			if (!mimeType.startsWith("image/") && proof4.getFileSize() <= 0) 
			{
				errors.add("proof4Error", new ActionMessage("error.proof4.invalid"));
				}
			System.out.println("Size : " + proof4.getFileSize() + "," + proof4.getContentType());
			System.out.println("Name : " + proof4.getFileName());
		}
		
		return errors;
	}
	
	
}
