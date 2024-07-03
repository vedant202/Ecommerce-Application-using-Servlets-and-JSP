package com.vedant_servelets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.services.ProductServicesImpl2;

import dtos.ProductDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class Main extends HttpServlet {
	private Logger log=LogManager.getLogger(Main.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		log.info(String.format("Request to /index has been received"));

		ProductServicesImpl2 productsServices = new ProductServicesImpl2();
		HashMap<String, List<ProductDto>> getAllProductsByCategeory=productsServices.getAllProductsByCategeory2();

		Gson gson = new Gson();
		req.setAttribute("products", gson.toJson(getAllProductsByCategeory));
		RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(req, resp);
	}
}
