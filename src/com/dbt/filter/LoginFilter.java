package com.dbt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbt.data.User;
import com.dbt.support.AESCrypto;
import com.dbt.support.Email;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		HttpServletResponse httpresponse = (HttpServletResponse) response;

		//System.out.println("LoginFilter : Called with session : "+session);
		if (session == null || session.getAttribute("user") == null) {
			//System.out.println("LoginFilter : Session is null or Unauthentic");
			Cookie[] cookies = httpRequest.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("auth_token")) {

						System.out.println("LoginFilter : Cookie Found");

						String[] tokens = AESCrypto.decrypt(c.getValue())
								.split(":");
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/login.do?rememberMe=No&email="
										+ tokens[0]
										+ "&password="
										+ tokens[1]
										+ "");

						try {
							dispatcher.forward(request, response);
							return;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Email.sendExceptionReport(e);
						}

						break;
					}
				}

			}
		} else if (session.getAttribute("otp") == null) {
			User u = (User) session.getAttribute("user");
			System.out.println("LoginFilter : User of type & name : "
					+ u.getFirstName() + "," + u.getType());
			httpresponse.sendRedirect("dashboard.jsp");
			return;
		}

		System.out.println("LoginFilter : Session Filter Called, resource : "
				+ httpRequest.getRequestURI());
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
