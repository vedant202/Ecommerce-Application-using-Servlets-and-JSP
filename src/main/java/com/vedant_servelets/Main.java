package com.vedant_servelets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;

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
		ProductsServices productsServices = new ProductsServicesImpl();
		HashMap<String, List<Product>> getAllProductsByCategeory=productsServices.getAllProductsByCategeory();


//		req.setAttribute("products", new Gson().toJson(products.getProducts()));

		Gson gson = new Gson();
		req.setAttribute("products", gson.toJson(getAllProductsByCategeory));
		RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(req, resp);
	}
}
