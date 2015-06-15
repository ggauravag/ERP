package com.dbt.processor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

import com.dbt.support.AESCrypto;

public class LoginProcessor extends RequestProcessor {
	@Override
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		boolean hasCookie = true;
		System.out.println("Request for some resource");
		if (request.getServletPath().equals("/login.do")) {
			System.out.println("Request for login.jsp");
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("auth_token")) {
					String[] tokens = AESCrypto.decrypt(c.getValue())
							.split(":");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/login.do?email="
									+ tokens[0] + "&password=" + tokens[1] + "");
					try {
						dispatcher.forward(request, response);
						return false;
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
		else
			return true;

		return hasCookie;
	}

}
