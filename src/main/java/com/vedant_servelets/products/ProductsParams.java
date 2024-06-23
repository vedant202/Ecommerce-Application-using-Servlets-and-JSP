package com.vedant_servelets.products;

import java.io.IOException;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductsParams extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Product Id :- "+req.getParameter("id"));
		long id = Long.parseLong(req.getParameter("id"));

		ProductsServices productsServices = new ProductsServicesImpl();
		Product product=productsServices.getProductById(id);
//		resp.getWriter().print(product);
		req.setAttribute("product", product);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/Product.jsp");
		requestDispatcher.forward(req, resp);
	}
}
