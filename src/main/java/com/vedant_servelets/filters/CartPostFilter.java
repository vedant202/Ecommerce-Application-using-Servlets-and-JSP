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

public class CartPostFilter implements Filter {

	private Logger log = LogManager.getLogger(CartPostFilter.class);


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub


		log.info("CardPostfilter got request");

		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpSession session=httpServletRequest.getSession(false);
		if(session==null) {
			httpServletResponse.getWriter().write("{\"status\":\"failure\"}");
			return;
		}

		String user = (String)session.getAttribute("user");
		String role = (String)session.getAttribute("role");
		System.out.println("Session email "+session.getAttribute("user"));
		System.out.println("Session role "+session.getAttribute("role"));

		if(StringUtils.isBlank(user)) {
			httpServletResponse.getWriter().write("{\"status\":\"failure\"}");
			return;
		}

  		if(StringUtils.equalsAny(role, "USER","ADMIN")) {
  			chain.doFilter(request, response);
		}else {
			httpServletResponse.getWriter().write("{\"status\":\"failure\"}");
			return;
		}




	}
}
