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
import javax.servlet.http.HttpSession;

import com.dbt.data.User;
import com.dbt.support.AESCrypto;

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
       
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("LoginFilter : Session is null or Unauthentic");
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

						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		} else {
			User u = (User) session.getAttribute("user");
			System.out.println("LoginFilter : User of type & name : " + u.getFirstName()
					+ "," + u.getType());
		}

		System.out.println("LoginFilter : Filter Called, resource : "
				+ httpRequest.getRequestURI());
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
