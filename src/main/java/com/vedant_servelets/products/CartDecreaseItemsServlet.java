package com.vedant_servelets.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;

import dtos.CartDto;
import dtos.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartDecreaseItemsServlet extends HttpServlet {

	private CartServices cartServices;

	public CartDecreaseItemsServlet() {
		// TODO Auto-generated constructor stub
		cartServices = new CartServicesImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Long.parseLong(req.getParameter("id"));
		Gson gson = new Gson();
		Optional<Cart> cart =this.cartServices.decreaseCartItems(id);
		CartDto cartDto=null;
		if(cart!=null) {
			if(!cart.isEmpty()) {
				cartDto = new CartDto(cart.get().getId(),new ProductDto(cart.get().getProducts().getId(),cart.get().getProducts().getTitle(),cart.get().getProducts().getDescription(),cart.get().getProducts().getCategory(),cart.get().getProducts().getPrice(),cart.get().getProducts().getDiscountPercentage(),cart.get().getProducts().getRating(),cart.get().getProducts().getStock()
						,cart.get().getProducts().getTags(),cart.get().getProducts().getBrand(),cart.get().getProducts().getSku(),cart.get().getProducts().getWeight(),cart.get().getProducts().getDimensions(),cart.get().getProducts().getWarrantyInformation(),cart.get().getProducts().getShippingInformation()
						,cart.get().getProducts().getAvailabilityStatus(),cart.get().getProducts().getReviews(),cart.get().getProducts().getReturnPolicy(),cart.get().getProducts().getMinimumOrderQuantity(),cart.get().getProducts().getImages()), cart.get().getItems());
			}
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		out.print(gson.toJson(cartDto));
		out.flush();

	}
}
