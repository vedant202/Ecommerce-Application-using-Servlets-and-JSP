package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.utils.DBUtils;

import dtos.CartTableDto;

public class CartServicesImpl implements CartServices {



	@Override
	public Optional<List<Cart>> getCartByUserId(User userId) {
		// TODO Auto-generated method stub
		return DBUtils.getCartByUserId(userId);
	}




	@Override
	public Optional<Cart> saveCart(CartTableDto c){
		return DBUtils.insertInToCart(c);
	}
	
	
	@Override
	public boolean deleteCartById(long id) {
		// TODO Auto-generated method stub
		
		return DBUtils.removeCart(id);
		
	} 
	
	@Override
	public Optional<Cart> increaseCartItems(long id) {
		// TODO Auto-generated method stub
		return DBUtils.increaseCartItems(id);
	}
	
	public Optional<Cart> decreaseCartItems(long id) {
		// TODO Auto-generated method stub
		return DBUtils.decreaseCartItems(id);
	}

}
