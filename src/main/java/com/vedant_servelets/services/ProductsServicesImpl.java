package com.vedant_servelets.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vedant_servelets.apis.GettingProductsDetails;
import com.vedant_servelets.apis.GettingProductsImpl;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;
import com.vedant_servelets.utils.DBUtils;

import dtos.ProductDto;

public class ProductsServicesImpl implements ProductsServices {

	private Logger log = LogManager.getLogger(ProductsServicesImpl.class);

	@Override
	public HashMap<String, List<Product>> getAllProductsByCategeory() {
		ArrayProducts products = null;
		log.info(String.format("Request to /products has been received"));
		log.info(String.format("Getting products"));

		JsonFileHandling fileHandling = new JsonFileHandling();
		products = fileHandling.getDataFromFile();
		if (products == null) {
			log.warn("Products is not present");
			GettingProductsDetails productsDetails = new GettingProductsImpl();
			products = productsDetails.getAllProducts();
		}

		ArrayList<Product> arrProducts = products.getProducts();

//		Set<String> setCatgeories=arrProducts.stream().map((i)->i.getCategory()).collect(Collectors.toSet());
		HashMap<String, List<Product>> mapProductsByCateg = new HashMap();
		arrProducts.stream().forEach(i -> {
			List<Product> prods = mapProductsByCateg.get(i.getCategory());
			if (prods == null) {
				prods = new ArrayList<>();
			}
			prods.add(i);
			mapProductsByCateg.put(i.getCategory(), prods);
		});

		return mapProductsByCateg;
	}

	@Override
	public Product getProductById(long id) {
		log.info(String.format("Getting product with id:- " + id));
		Optional<Product> optionalPrd = DBUtils.getProductById(id);

		if (optionalPrd.isEmpty()) {
			log.warn("Product with " + id + " is not present");
			return null;
		}

		return optionalPrd.get();
	}

	// Get product without user and cart information

	public Product getProductById2(long id) {
		log.info(String.format("Getting product with id:- " + id));
		Optional<Product> optionalPrd = DBUtils.getProductById2(id);

		if (optionalPrd.isEmpty()) {
			log.warn("Product with " + id + " is not present");
			return null;
		}

		return optionalPrd.get();
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub

		return DBUtils.saveProduct(product);
	}

	public List<ProductDto> getProducts(){

		List<Product> products=DBUtils.getAllProducts().orElse(null);
		List<ProductDto> prods=new ArrayList<>();
		if(products!=null) {
			products.stream().forEach(i->{
				prods.add(new ProductDto(i.getId(),i.getTitle(),i.getDescription(),i.getCategory(),i.getPrice(),i.getDiscountPercentage(),i.getRating(),i.getStock()
						,i.getTags(),i.getBrand(),i.getSku(),i.getWeight(),i.getDimensions(),i.getWarrantyInformation(),i.getShippingInformation()
						,i.getAvailabilityStatus(),i.getReviews(),i.getReturnPolicy(),i.getMinimumOrderQuantity(),i.getImages()));
			});
		}

		return prods;
	}
	
	public double getMaxProductPrice() {
		return DBUtils.getMaxPriceProducts();
	}

	public long getAllProductsCount() {
		return DBUtils.getAllProductsCount();
	}

	public List<ProductDto> getProducts2(int pageNo,int maxResult){

		List<Product> products=DBUtils.getAllProducts2(pageNo,maxResult).orElse(null);
		List<ProductDto> prods=new ArrayList<>();
		if(products!=null) {
			products.stream().forEach(i->{
				prods.add(new ProductDto(i.getId(),i.getTitle(),i.getDescription(),i.getCategory(),i.getPrice(),i.getDiscountPercentage(),i.getRating(),i.getStock()
						,i.getTags(),i.getBrand(),i.getSku(),i.getWeight(),i.getDimensions(),i.getWarrantyInformation(),i.getShippingInformation()
						,i.getAvailabilityStatus(),i.getReviews(),i.getReturnPolicy(),i.getMinimumOrderQuantity(),i.getImages()));
			});
		}

		return prods;
	}
	
	public List<ProductDto> getProducts2(int pageNo,int maxResult,List<Integer> prices){

		List<Product> products=DBUtils.getAllProducts2(pageNo,prices,maxResult).orElse(null);
		List<ProductDto> prods=new ArrayList<>();
		if(products!=null) {
			products.stream().forEach(i->{
				prods.add(new ProductDto(i.getId(),i.getTitle(),i.getDescription(),i.getCategory(),i.getPrice(),i.getDiscountPercentage(),i.getRating(),i.getStock()
						,i.getTags(),i.getBrand(),i.getSku(),i.getWeight(),i.getDimensions(),i.getWarrantyInformation(),i.getShippingInformation()
						,i.getAvailabilityStatus(),i.getReviews(),i.getReturnPolicy(),i.getMinimumOrderQuantity(),i.getImages()));
			});
		}

		return prods;
	}

	public Product updateProductById(Product product) {
		return DBUtils.updateProduct(product).orElseThrow(() -> new RuntimeException("Product with id "+ product.getId() +" and title "+ product.getTitle() +" is not present."));
	}

	public void deleteProductById(long id) {
		DBUtils.deleteProductById(id);
	}
	
	
//	we are fetching only id and title from the database for navbar search results
	public List<HashMap>  getProductIdAndTitle(String reqParam) {
		return DBUtils.getProductTitlesAndId(reqParam);
	}
}
