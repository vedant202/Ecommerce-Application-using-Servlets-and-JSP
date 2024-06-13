package com.vedant_servelets.AuthenticationAndAutheriztion;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet{

	private static HashMap<String, String[]> users = new HashMap<>();
	private Logger log=LogManager.getLogger(Login.class);


	static {
		System.out.println("Login static blocked initialized");
		String[] str1 = {"vedant","user"};
		users.put("vedant@gmail.com",str1);
		String[] str2 = {"test","user"};
		users.put("test@gmail.com",str2);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.entry();
		System.out.println("Post request to login");
		log.info("Post Request to logging is being made");
		System.out.println(req.getParameter("email"));
		System.out.println(req.getParameter("password"));

		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		log.info(String.format("Login request made by User %s and Password %s", email, pass));
		if(!users.containsKey(email)) {
			System.out.println("User is not present");
			log.error("User %s and password %s is not present",email,pass);

			resp.getWriter().println("<h1>User is not present</h1>");
			return;
		}

		String[] userArr = users.get(email);
		String userPass = userArr[0];
		String userRole = userArr[1];

		if(!userPass.equals(pass)) {
			System.out.println("User password not matched");
			resp.getWriter().println("<h1>User is not present</h1>");
			return;
		}
		log.info(String.format("User with email %s and Role %s is logged in and redirected to index ",email,userRole ));
		HttpSession session  = req.getSession();
		session.setAttribute("user", email);
		session.setAttribute("role",userRole);
		resp.sendRedirect("/FilterTuts/index");




	}
}
