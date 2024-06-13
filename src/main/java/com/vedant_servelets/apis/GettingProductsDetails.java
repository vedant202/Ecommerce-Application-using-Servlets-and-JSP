package com.vedant_servelets.apis;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;

public interface GettingProductsDetails {
	ArrayProducts getAllProducts();

	Product getProduct();
}
