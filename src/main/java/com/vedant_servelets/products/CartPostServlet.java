package com.vedant_servelets.products;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servlet mapping in web.xml

public class CartPostServlet extends HttpServlet {

	private Logger log=LogManager.getLogger(CartPostServlet.class);
	
	private JsonObject bufferToJson(HttpServletRequest req) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			req.getReader().lines().forEach(i->stringBuilder.append(i));
			System.out.println(stringBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString = stringBuilder.toString();
		System.out.println("jsonString:- "+jsonString);
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		System.out.println("jsonObject:- "+jsonObject);
		return jsonObject;
	}
	
	private long getProductId(JsonObject jsonObject) {
		
		long id = jsonObject.get("id").getAsLong();
		return id;
	}
	
	private long getProductItemsCount(JsonObject jsonObject) {
		JsonElement items =  jsonObject.has("items")?jsonObject.get("items"):null;
		
		if(items==null) {
			System.out.println("items is null");
			return 1;
		}
		long itemsCount = items.getAsLong();
		return itemsCount;
	}
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsonObject jsonObject= bufferToJson(req);
		UserServices userServices = new UserServicesImpl();
		Optional<User> user = userServices.getUserByEmail((String)req.getSession(false).getAttribute("user"));
		if(user.isEmpty()) {
			resp.getWriter().write("{\"status\":\"failure\"}");
			return;
		}
		
		long productId = getProductId(jsonObject);
		System.out.println(productId);
		long items = getProductItemsCount(jsonObject);

		CartServices cartServices = new CartServicesImpl();
		
		ProductsServices productsServices = new ProductsServicesImpl();

		

		Cart cart = new Cart();
		cart.setProductId(productsServices.getProductById(productId));
		cart.setItems(items);
		cart.setUserId(user.get());
		
		cartServices.saveCart(cart);

		resp.getWriter().write("{\"status\":\"success\"}");
//		resp.sendRedirect("/FilterTuts/orders");

	}

}
