package com.vedant_servelets.services;

import java.util.HashMap;
import java.util.List;

import com.vedant_servelets.entities.Product;

public interface ProductsServices {

	HashMap<String, List<Product>> getAllProductsByCategeory();
	Product getProductById(long id);
	Product save(Product product);



}
