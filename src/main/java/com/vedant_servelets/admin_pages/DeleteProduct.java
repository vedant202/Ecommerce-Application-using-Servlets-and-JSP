package com.vedant_servelets.admin_pages;

import java.io.IOException;

import com.vedant_servelets.services.ProductsServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/delete/product")
public class DeleteProduct extends HttpServlet {

	private ProductsServicesImpl servicesImpl;

	public DeleteProduct() {
		// TODO Auto-generated constructor stub
		this.servicesImpl = new ProductsServicesImpl();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Long id = Long.parseLong(req.getParameter("id"));

			this.servicesImpl.deleteProductById(id);

			resp.sendRedirect("/FilterTuts/admin");
		}catch (Exception e) {
			// TODO: handle exception
			resp.getWriter().write("Product with id :- " + req.getParameter("id")+" is not present");
		}

	}
}
