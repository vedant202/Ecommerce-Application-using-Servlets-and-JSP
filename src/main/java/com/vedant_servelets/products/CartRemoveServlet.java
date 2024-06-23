package com.vedant_servelets.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartRemoveServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4205239937467315514L;
	private Gson gson = new Gson();
	
	private CartServices cartServices;
	
	public CartRemoveServlet() {
		// TODO Auto-generated constructor stub
		this.cartServices = new CartServicesImpl();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Remove cart servlet");

		int id = Integer.parseInt(req.getParameter("id"));
		
		
		System.out.println("Cart id :- "+id);
		
		boolean cartDeleted = this.cartServices.deleteCartById(id);
		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		if(cartDeleted) {
			out.print(gson.toJson(Map.of("success", true)));
			out.flush();
			
		}
		else {
			out.print(gson.toJson(Map.of("success", false)));
			out.flush();
		}
		
		
	}
}
