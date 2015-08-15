package com.dbt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbt.data.User;
import com.dbt.support.TestMemory;

/**
 * Servlet Filter implementation class RoleFilter
 */
public class RoleFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RoleFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httprequest.getSession();
		
		String uri = httprequest.getRequestURI();
		TestMemory.printMemory();
		
		System.out.println("RoleFilter : Called with session "+session);
		if((uri.contains(".jsp") || uri.contains(".do")) && !uri.contains("login"))
		{
			if(session != null && session.getAttribute("user") != null)
			{
				User user = (User)session.getAttribute("user");
				String userType = user.getType();
				if((userType.equals("OPERATOR") || userType.equals("DBA")) && uri.contains("owner"))
				{
					httpResponse.sendRedirect(httprequest.getContextPath() + "/Unauthorize.jsp");
					return;
				}
			}
		}
		System.out.println("Resource Invoked Finally - "+httprequest.getRequestURI());
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
