package com.vedant_servelets.products;

import java.io.IOException;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;

import dtos.ProductDto;
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
		ProductDto productDto = new ProductDto(product.getId(),product.getTitle(),product.getDescription(),product.getCategory(),product.getPrice(),product.getDiscountPercentage(),product.getRating(),product.getStock()
				,product.getTags(),product.getBrand(),product.getSku(),product.getWeight(),product.getDimensions(),product.getWarrantyInformation(),product.getShippingInformation()
				,product.getAvailabilityStatus(),product.getReviews(),product.getReturnPolicy(),product.getMinimumOrderQuantity(),product.getImages());
//		resp.getWriter().print(product);
		System.out.println("productDto12 :- "+productDto);
		req.setAttribute("product", productDto);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/Product.jsp");
		requestDispatcher.forward(req, resp);
	}
}
