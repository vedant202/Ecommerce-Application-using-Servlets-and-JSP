package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.utils.DBUtils;

public class CartServicesImpl implements CartServices {


	@Override
	public Optional<List<Cart>> getCartByUserId(User userId) {
		// TODO Auto-generated method stub
		return DBUtils.getCartByUserId(userId);
	}
	
	@Override
	public Optional<Cart> saveCart(Cart c){
		return DBUtils.insertInToCart(c);
	}

}
