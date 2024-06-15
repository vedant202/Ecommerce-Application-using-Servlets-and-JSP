package com.vedant_servelets.AuthenticationAndAutheriztion;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import com.vedant_servelets.entities.Address;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUp extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/Signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String address1 = req.getParameter("address1");
		String address2 = req.getParameter("address2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		String number = req.getParameter("number");
		
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(number);
		System.out.println(password);
		
		
		if (StringUtils.isEmpty(number)) {
			resp.getWriter().write("number is empty");
			return;
		}
		
		if(number.matches("\\d{10}")) {
			System.out.println("Number is valid");
		}
		
		if (StringUtils.isEmpty(email)) {
			resp.getWriter().write("email is empty");
			return;
		}
		
		if(!EmailValidator.getInstance().isValid(email)) {
			resp.getWriter().write("email is not valid");
			return;
		}
		
		if (StringUtils.isEmpty(password)) {
			resp.getWriter().write("password is empty");
			return;
		}
		
		if (StringUtils.isEmpty(cpassword)) {
			resp.getWriter().write("confirm password is empty");
			return;
		}
		
		if(!password.equals(cpassword)) {
			resp.getWriter().write("wrong password");
			return;
		}
		
		
		UserServices services = new UserServicesImpl();
		User u = new User();
		u.setFname(fname);
		u.setLname(lname);
		u.setEmail(email);
		
		Address address= new Address();
		address.setAddress1(address1);
		address.setAddress2(address2);
		address.setCity(city);
		address.setState(state);
	
		u.setAddress(address);
		u.setPassword(password);
		u.setPhoneNumber(number);
		
		u.setDate(Timestamp.valueOf(LocalDateTime.now()));
		
		address.setUserId(u);
		
		Optional<User> user=services.addUser(u);
		if(user.isEmpty()) {
			resp.getWriter().write("Exception occured");
			return;
		}
		
		resp.sendRedirect("/FilterTuts/index");


	}
}
