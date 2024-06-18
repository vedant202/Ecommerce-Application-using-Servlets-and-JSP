package com.vedant_servelets.products;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import dtos.CartDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartGetService extends HttpServlet {
	private ProductsServices productsServices=null;
	private CartServices cartServices=null;
	private UserServices userServices=null;
	private Logger log=LogManager.getLogger(CartGetService.class);

	public CartGetService() {
		this.productsServices = new ProductsServicesImpl();
		this.cartServices = new CartServicesImpl();
		this.userServices = new UserServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(false);

		String user_email=(String)session.getAttribute("user");
		System.out.println(user_email);
		String[] arr=StringUtils.split(user_email,'@');
		String userName = arr[0];
		System.out.println(userName);
		Optional<User> user = this.userServices.getUserByEmail((String)session.getAttribute("user"));
		System.out.println("user :-"+user );
		Optional<List<Cart>> carts=this.cartServices.getCartByUserId(user.get());
		System.out.println(carts);
		List<CartDto> cartDtos=null;

		if(!carts.isEmpty()) {
			cartDtos=carts.get().stream().map(i->new CartDto(i.getProductId(), i.getItems())).collect(Collectors.toList());

		}


		req.setAttribute("carts", cartDtos);
		req.setAttribute("userName", userName);
		req.getRequestDispatcher("/WEB-INF/Orders.jsp").forward(req, resp);
	}

}
