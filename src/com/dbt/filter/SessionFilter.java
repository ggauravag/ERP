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

import org.apache.log4j.Logger;

import com.dbt.support.TestMemory;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

	static Logger logger = Logger.getLogger(SessionFilter.class.getName());

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httprequest.getSession(false);
		String uri = httprequest.getRequestURI();
		TestMemory.printMemory();

		if ((uri.contains(".jsp") || uri.contains(".do"))
				&& !uri.contains("login") && !uri.contains("forgotPassword")
				&& !uri.contains("ForgotPassword") && !uri.contains("feedback.jsp") && !uri.contains("Submitfeedback")) {
			logger.info("SessionFilter : Called with session " + session);
			if (session == null || session.getAttribute("user") == null) {
				httpResponse.sendRedirect(httprequest.getContextPath()
						+ "/login.jsp");
				return;
			} else if (session.getAttribute("user") != null) {
				String otp = (String) session.getAttribute("otp");
				if (otp != null) {
					httpResponse.sendRedirect(httprequest.getContextPath()
							+ "/login.jsp");
					return;
				}
			}
		}

		logger.info("SessionFilter : Resource Invoked, resource : "
				+ httprequest.getRequestURI());
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
