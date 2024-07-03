package com.vedant_servelets.admin_pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/add_product")
public class AddProduct extends HttpServlet{
	private ProductsServices productsServices;
	public AddProduct() {
		// TODO Auto-generated constructor stub
		productsServices = new ProductsServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatch= req.getRequestDispatcher("/WEB-INF/AddProduct.jsp");
		dispatch.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tit1e = req.getParameter("title");
		String availabilityStatus = req.getParameter("availabilityStatus");
		String brand = req.getParameter("brand");
		String category = req.getParameter("category");
		String description = req.getParameter("description");
		String discountPercentage = req.getParameter("discountPercentage");
		String minimumOrderQuantity = req.getParameter("minimumOrderQuantity");
		String price = req.getParameter("price");
		String rating = req.getParameter("rating");
		String returnPolicy = req.getParameter("returnPolicy");
		String shippingInformation = req.getParameter("shippingInformation");
		String sku = req.getParameter("sku");
		String stock = req.getParameter("stock");
		String tags = req.getParameter("tags");
		String warrantyInformation = req.getParameter("warrantyInformation");
		String weight = req.getParameter("weight");
		String imageUrl = req.getParameter("image-url");
		System.out.println("imageUrl :- "+imageUrl);


		Product product = new Product();

		product.setTitle(tit1e);
		product.setBrand(brand);
		product.setAvailabilityStatus(availabilityStatus);
		product.setMinimumOrderQuantity(minimumOrderQuantity);
		product.setDiscountPercentage(Double.parseDouble(discountPercentage));
		product.setPrice(Double.parseDouble(price));
		product.setRating(Double.parseDouble(rating));
		product.setReturnPolicy(returnPolicy);
		product.setShippingInformation(shippingInformation);
		product.setSku(sku);
		product.setStock(Integer.parseInt(stock));
		product.setTags(List.of(tags));
		product.setWarrantyInformation(warrantyInformation);
		product.setWeight(Double.parseDouble(weight));
		product.setDescription(description);
		product.setCategory(category);
		List<String> images = new ArrayList<>();
		images.add(imageUrl);
		product.setImages(images);

		productsServices.save(product);

		resp.sendRedirect("/FilterTuts/admin/add_product");
	}
}
