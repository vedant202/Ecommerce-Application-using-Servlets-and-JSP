package com.vedant_servelets.products;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.CartServices;
import com.vedant_servelets.services.CartServicesImpl;
import com.vedant_servelets.services.ProductsServices;
import com.vedant_servelets.services.ProductsServicesImpl;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import dtos.CartDto;
import dtos.ProductDto;
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

		// Getting user By email
		Optional<User> user = this.userServices.getUserByEmail((String)session.getAttribute("user"));
		System.out.println("user :-"+user );

		//Getting cart by user id
		Optional<List<Cart>> cart=this.cartServices.getCartByUserId(user.get());

		List<CartDto> cartDtos=null;

		

		// Checking if cart is empty
		if(cart!=null) {
			
			if(!cart.isEmpty()) {
				cartDtos=cart.get().stream().map(i->new CartDto(i.getId(),new ProductDto(i.getProducts().getId(),i.getProducts().getTitle(),i.getProducts().getDescription(),i.getProducts().getCategory(),i.getProducts().getPrice(),i.getProducts().getDiscountPercentage(),i.getProducts().getRating(),i.getProducts().getStock()
						,i.getProducts().getTags(),i.getProducts().getBrand(),i.getProducts().getSku(),i.getProducts().getWeight(),i.getProducts().getDimensions(),i.getProducts().getWarrantyInformation(),i.getProducts().getShippingInformation()
						,i.getProducts().getAvailabilityStatus(),i.getProducts().getReviews(),i.getProducts().getReturnPolicy(),i.getProducts().getMinimumOrderQuantity(),i.getProducts().getImages()), i.getItems())).collect(Collectors.toList());

			}
			
//			if(cart.isPresent()) {
//				for(Cart i: cart.get()) {
//					cartDtos.add(new CartDto(new ProductDto(i.getProducts().getId(),i.getProducts().getTitle(),i.getProducts().getDescription(),i.getProducts().getCategory(),i.getProducts().getPrice(),i.getProducts().getDiscountPercentage(),i.getProducts().getRating(),i.getProducts().getStock()
//							,i.getProducts().getTags(),i.getProducts().getBrand(),i.getProducts().getSku(),i.getProducts().getWeight(),i.getProducts().getDimensions(),i.getProducts().getWarrantyInformation(),i.getProducts().getShippingInformation()
//							,i.getProducts().getAvailabilityStatus(),i.getProducts().getReviews(),i.getProducts().getReturnPolicy(),i.getProducts().getMinimumOrderQuantity(),i.getProducts().getImages()),i.getItems()));
//				}
//
//			}
		}

		//Sending Json response
		req.setAttribute("carts", new Gson().toJson(cartDtos));
		req.setAttribute("userName", userName);
		req.getRequestDispatcher("/WEB-INF/Orders.jsp").forward(req, resp);
	}

}
