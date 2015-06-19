package com.dbt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbt.dao.FundDAO;

public class CapitalServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			int capitalAmount = Integer.parseInt(request.getParameter("inputAmount"));
			String capitalLender = request.getParameter("inputLender");
			int capitalRate = Integer.parseInt(request.getParameter("inputRate"));
			
			boolean capitalAdded = FundDAO.addCapital(capitalAmount, capitalRate, capitalLender);
			
			if(capitalAdded == true)
			{
				System.out.println("capital details added successfully to database");
				request.setAttribute("capitalStatus", "Success");
			}
			else
			{
				System.out.println("Unable to add capital details to database");
				request.setAttribute("capitalStatus", "Failure");
			}
			RequestDispatcher rd = request.getRequestDispatcher("owner/AddCapital.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Exception in CapitalServlet.java");
			e.printStackTrace();
		}
		
	}

}
