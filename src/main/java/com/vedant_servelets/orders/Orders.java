package com.vedant_servelets.orders;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Orders extends HttpServlet{
	private Logger log=LogManager.getLogger(Orders.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Orders class executed");
		log.info("Get request to /Orders.jsp");

		HttpSession session = req.getSession(false);

		String user_email=(String)session.getAttribute("user");
		String[] arr=StringUtils.split(user_email,'@');
		String userName = arr[0];

		req.setAttribute("username", userName);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Orders.jsp");
		dispatcher.forward(req, resp);

	}

}
