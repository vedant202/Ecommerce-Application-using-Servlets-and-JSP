package com.vedant_servelets.products;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PlaceOrderServlet extends HttpServlet {

	private UserServices userServices;
	private CartServices cartServices;

	public PlaceOrderServlet() {
		this.userServices = new UserServicesImpl();
		this.cartServices = new CartServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(false);
		Gson gson = new Gson();

		String user_email=(String)session.getAttribute("user");
		System.out.println(user_email);
		String[] arr=StringUtils.split(user_email,'@');
		String userName = arr[0];
		System.out.println(userName);

		//Getting User By Email
		Optional<User> optionalUser=this.userServices.getUserByEmail(user_email);

		//Getting cart By user Id
		Optional<List<Cart>> carts=this.cartServices.getCartByUserId(optionalUser.get());
		Map<String,Map<String,String>> cartMap = new HashMap();
		double totalPrice=0;
		int totalItems = 0;
		if(carts!=null) {
			if(!carts.isEmpty()) {
				for(Cart i:carts.get()) {
					Map<String,String> map = new HashMap<>();
					totalPrice += i.getProducts().getPrice()*i.getItems();
					totalItems += i.getItems();
					map.put("items",Long.toString(i.getItems()));
					map.put("price",Double.toString(i.getProducts().getPrice()*i.getItems()));
					cartMap.put(i.getProducts().getTitle(), map);
				}
			}
		}

		req.setAttribute("userName", userName);
		req.setAttribute("cartMap", gson.toJson(Map.of("Total Price",totalPrice,"totalItems",totalItems,"listItems",cartMap)));
		req.getRequestDispatcher("/WEB-INF/Purchase.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
