package com.vedant_servelets.admin_pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.services.ProductsServicesImpl;

import dtos.ProductDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/update/product")
public class UpdateProduct extends HttpServlet {

	private ProductsServicesImpl servicesImpl;

	public UpdateProduct() {
		// TODO Auto-generated constructor stub
		this.servicesImpl = new ProductsServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		System.out.println(id);

		Product product = this.servicesImpl.getProductById2(Long.parseLong(id));

		ProductDto productDto = new ProductDto(product.getId(), product.getTitle(), product.getDescription(),
				product.getCategory(), product.getPrice(), product.getDiscountPercentage(), product.getRating(),
				product.getStock(), product.getTags(), product.getBrand(), product.getSku(), product.getWeight(),
				product.getDimensions(), product.getWarrantyInformation(), product.getShippingInformation(),
				product.getAvailabilityStatus(), product.getReviews(), product.getReturnPolicy(),
				product.getMinimumOrderQuantity(), product.getImages());

		req.setAttribute("product", new Gson().toJson(productDto));
		RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/AdminUpdateProduct.jsp");
		dispatch.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
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
		product.setId(Long.parseLong(id));
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

		try {
			this.servicesImpl.updateProductById(product);

		} catch (RuntimeException e) {
			// TODO: handle exception
			resp.getWriter().write("Product "+ product.getTitle() +" is not present");
			return;
		}

		resp.sendRedirect("/FilterTuts/admin");
	}
}
