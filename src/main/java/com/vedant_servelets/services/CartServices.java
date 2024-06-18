package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;

public interface CartServices {
	public Optional<List<Cart>> getCartByUserId(User userId);
	public Optional<Cart> saveCart(Cart c);
}
