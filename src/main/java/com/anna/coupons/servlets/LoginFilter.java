package com.anna.coupons.servlets;

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

public class LoginFilter implements Filter {

	 public void CORSFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	            throws IOException, ServletException {
		 chain.doFilter(servletRequest, servletResponse);
	 }
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false); //If session exists, then it returns the reference of that session object, if not, this methods will return null.
		String pageRequested = req.getRequestURL().toString();
		String pageMethod = req.getMethod();
		String customerRegister = req.getRequestURL().toString();
		String companyRegister = req.getRequestURL().toString();
		if (session != null || pageRequested.endsWith("/login")|| 
				pageMethod.equals("OPTIONS") || 
				customerRegister.endsWith("/customer") || 
				companyRegister.endsWith("/company")) {
			chain.doFilter(request, response);
			return;
		}
		res.setStatus(401);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}