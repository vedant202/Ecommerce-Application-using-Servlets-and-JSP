package com.vedant_servelets.admin_pages;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.vedant_servelets.services.ProductsServicesImpl;

import dtos.ProductDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin")
public class AdminHome extends HttpServlet {
	ProductsServicesImpl servicesImpl;
	public AdminHome() {
		// TODO Auto-generated constructor stub
		servicesImpl = new ProductsServicesImpl();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ProductDto> products=servicesImpl.getProducts();

		Gson gson = new Gson();
		req.setAttribute("products", gson.toJson(products));
		RequestDispatcher dispatch= req.getRequestDispatcher("/WEB-INF/AdminHome.jsp");
		dispatch.forward(req, resp);
	}
}
