package com.vedant_servelets.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.utils.DBUtils;

public class ProductServicesImpl2 extends ProductsServicesImpl implements ProductsServices {
	private Logger log=LogManager.getLogger(ProductServicesImpl2.class);
	@Override
	public HashMap<String, List<Product>> getAllProductsByCategeory() {
		log.info(String.format("Getting products"));

		List<Product> products=null;
		Optional<List<Product>> optionalProd=DBUtils.getAllProducts();
		if(optionalProd.isEmpty()) {
			log.info(String.format("Product is empty"));
			return null;
		}
		products = optionalProd.get();

		HashMap<String, List<Product>> mapProductsByCateg = new HashMap();
		products.stream().forEach(i->{
			List<Product> prods =  mapProductsByCateg.get(i.getCategory());
			if(prods==null) {
				prods = new ArrayList<>();
			}
			prods.add(i);
			mapProductsByCateg.put(i.getCategory(), prods);
		});


		return mapProductsByCateg;

	}

}
