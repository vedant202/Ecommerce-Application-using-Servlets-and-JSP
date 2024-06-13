package com.vedant_servelets.filters;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyFilter implements Filter {
	private Logger log = LogManager.getLogger(MyFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("MyFilter resuest got");

		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		System.out.println(" httpServletRequest :- "+httpServletRequest.getLocalAddr()+" "+httpServletRequest.getLocalName()+" "+httpServletRequest.getServerName()+httpServletRequest.getServletPath());
		log.info(String.format("%s reuest made", (httpServletRequest.getServerName()+httpServletRequest.getServletPath())));
		HttpSession session=httpServletRequest.getSession(false);

		if(session==null) {
			System.out.println();
			httpServletResponse.sendRedirect("/FilterTuts/login");
			return;
		}


		String user = (String)session.getAttribute("user");
		String role = (String)session.getAttribute("role");
		System.out.println("Session email "+session.getAttribute("user"));
		System.out.println("Session role "+session.getAttribute("role"));


		if(StringUtils.isBlank(user)) {
			httpServletResponse.sendRedirect("/FilterTuts/login");
			return;
		}

  		if(StringUtils.equalsAny(role, "user","admin")) {
  			chain.doFilter(request, response);
		}else {
			httpServletResponse.sendRedirect("/FilterTuts/login");
			return;
		}



		System.out.println("MyFilter sends the response");



	}

}
