package com.vedant_servelets.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vedant_servelets.entities.Product;
import com.vedant_servelets.utils.DBUtils;

import dtos.ProductDto;

public class ProductServicesImpl2 extends ProductsServicesImpl implements ProductsServices {
	private Logger log=LogManager.getLogger(ProductServicesImpl2.class);


// This functions categories product with the help of java and not by sql where all product is fetched from database
//	Not able to fetch reviews
	public HashMap<String, List<ProductDto>> getAllProductsByCategeory2() {

		log.info(String.format("Getting products"));

		List<Product> products=null;
		Optional<List<Product>> optionalProd=DBUtils.getAllProducts();
		if(optionalProd.isEmpty()) {
			log.info(String.format("Product is empty"));
			return null;
		}
		products = optionalProd.get();

		HashMap<String, List<ProductDto>> mapProductsByCateg = new HashMap();
		products.stream().forEach(i->{
			List<ProductDto> prods =  mapProductsByCateg.get(i.getCategory());
			if(prods==null) {
				prods = new ArrayList<>();
			}

//			Long id, String title, String description, String category, double price,
//			double discountPercentage, double rating, int stock, List<String> tags, String brand, String sku,
//			double weight, Dimensions dimensions, String warrantyInformation, String shippingInformation,
//			String availabilityStatus, List<Reviews> reviews, String returnPolicy, String minimumOrderQuantity,
//			List<String> images

			prods.add(new ProductDto(i.getId(),i.getTitle(),i.getDescription(),i.getCategory(),i.getPrice(),i.getDiscountPercentage(),i.getRating(),i.getStock()
					,i.getTags(),i.getBrand(),i.getSku(),i.getWeight(),i.getDimensions(),i.getWarrantyInformation(),i.getShippingInformation()
					,i.getAvailabilityStatus(),i.getReviews(),i.getReturnPolicy(),i.getMinimumOrderQuantity(),i.getImages()));

			mapProductsByCateg.put(i.getCategory(),prods);
		});


		return mapProductsByCateg;

	}

	public HashMap<String, List<ProductDto>> getAllProductsByCategoryBySql() {
		List<Product> products=null;

		Optional<List<Product>> optionalProd=DBUtils.getAllProductsSortByCreatedAtAndCategory();
		if(optionalProd.isEmpty()) {
			log.info(String.format("Product is empty"));
			return null;
		}
		products = optionalProd.get();

		HashMap<String, List<ProductDto>> mapProductsByCateg = new HashMap();
		products.stream().forEach(i->{
			List<ProductDto> prods =  mapProductsByCateg.get(i.getCategory());
			if(prods==null) {
				prods = new ArrayList<>();
			}

			prods.add(new ProductDto(i.getId(),i.getTitle(),i.getDescription(),i.getCategory(),i.getPrice(),i.getDiscountPercentage(),i.getRating(),i.getStock()
					,i.getTags(),i.getBrand(),i.getSku(),i.getWeight(),i.getDimensions(),i.getWarrantyInformation(),i.getShippingInformation()
					,i.getAvailabilityStatus(),null,i.getReturnPolicy(),i.getMinimumOrderQuantity(),i.getImages()));

			mapProductsByCateg.put(i.getCategory(),prods);
		});


		return mapProductsByCateg;	}

}
