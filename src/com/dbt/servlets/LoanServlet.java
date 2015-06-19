package com.dbt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbt.dao.FundDAO;

public class LoanServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		
			int loanAmount = Integer.parseInt(request.getParameter("inputAmount"));
			int loanTenure = Integer.parseInt(request.getParameter("inputTenure"));
			int loanInstallment = Integer.parseInt(request.getParameter("inputInstallement"));
			int loanInterest = Integer.parseInt(request.getParameter("inputInterest"));
			
			boolean loanAdded = FundDAO.addLoan(loanAmount, loanTenure, loanInstallment, loanInterest);
			
			if(loanAdded == true)
			{
				System.out.println("Loan details added successfully to database");
				request.setAttribute("loanStatus", "Success");
			}
			else
			{
				System.out.println("Unable to add loan details to database");
				request.setAttribute("loanStatus", "Failure");
			}
			RequestDispatcher rd = request.getRequestDispatcher("owner/AddLoan.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoanServlet.java");
			e.printStackTrace();
		}
	}
}
