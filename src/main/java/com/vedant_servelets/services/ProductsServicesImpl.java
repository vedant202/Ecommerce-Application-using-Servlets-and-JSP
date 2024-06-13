package com.vedant_servelets.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vedant_servelets.apis.GettingProductsDetails;
import com.vedant_servelets.apis.GettingProductsImpl;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;

public class ProductsServicesImpl implements ProductsServices {

	private Logger log=LogManager.getLogger(ProductsServicesImpl.class);

	@Override
	public HashMap<String, List<Product>> getAllProductsByCategeory() {
		ArrayProducts products=null;
		log.info(String.format("Request to /products has been received"));
		log.info(String.format("Getting products"));

		JsonFileHandling fileHandling=new JsonFileHandling();
		products =fileHandling.getDataFromFile();
		if(products==null) {
			log.warn("Products is not present");
			GettingProductsDetails productsDetails = new GettingProductsImpl();
			products=productsDetails.getAllProducts();
		}

		ArrayList<Product> arrProducts=products.getProducts();

//		Set<String> setCatgeories=arrProducts.stream().map((i)->i.getCategory()).collect(Collectors.toSet());
		HashMap<String, List<Product>> mapProductsByCateg = new HashMap();
		arrProducts.stream().forEach(i->{
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
