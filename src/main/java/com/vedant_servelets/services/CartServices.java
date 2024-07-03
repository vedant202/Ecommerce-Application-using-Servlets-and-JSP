package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;

import dtos.CartTableDto;

public interface CartServices {
	public Optional<List<Cart>> getCartByUserId(User userId);
	public Optional<Cart> saveCart(CartTableDto c);
	public boolean deleteCartById(long id);
	public Optional<Cart> increaseCartItems(long id);
	public Optional<Cart> decreaseCartItems(long id);

}
