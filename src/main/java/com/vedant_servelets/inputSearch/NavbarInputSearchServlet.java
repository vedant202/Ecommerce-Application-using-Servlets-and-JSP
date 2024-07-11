package com.vedant_servelets.inputSearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.vedant_servelets.services.ProductsServicesImpl;
import com.vedant_servelets.utils.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/s")
public class NavbarInputSearchServlet extends HttpServlet {
	
	private ProductsServicesImpl productsServicesImpl;
	public NavbarInputSearchServlet() {
		// TODO Auto-generated constructor stub
		this.productsServicesImpl = new ProductsServicesImpl();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String reqParam = req.getParameter("q");
		System.out.println(reqParam);
		List<HashMap>  productAndTitleMap=this.productsServicesImpl.getProductIdAndTitle(reqParam);
		System.out.println();
		
		resp.getWriter().write(new Gson().toJson(productAndTitleMap));
	}
	
}
