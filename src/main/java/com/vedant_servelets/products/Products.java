package com.vedant_servelets.products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.services.ProductsServicesImpl;

import dtos.ProductDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Products extends HttpServlet {

	private Logger log=LogManager.getLogger(Products.class);
	private ProductsServicesImpl servicesImpl ;
	public Products() {
		// TODO Auto-generated constructor stub
		this.servicesImpl = new ProductsServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info(String.format("Request to /products has been received"));
		log.info(String.format("Getting products"));

		String pageParam = req.getParameter("page");
		List<Integer> pricesFilt = new ArrayList<>();
//		This var is made because it checks weather to pass response with all filters and pagination
// 		if it is true or if false only products
		boolean stay = false;

		if(req.getParameter("stay")!=null&&!req.getParameter("stay").isBlank()) {
			stay = Boolean.valueOf(req.getParameter("stay"));
		}

		if(req.getParameter("price")!=null&&!req.getParameter("price").isBlank()) {
			String strPrices = req.getParameter("price");
			String[] splitPrices = strPrices.split("-");

			for(String i:splitPrices) {
				pricesFilt.add(Integer.parseInt(i.trim()));
			}
		}
		List<String> cates = new ArrayList<>();
		if(req.getParameter("categories")!=null && !req.getParameter("categories").isBlank()) {
			String strCates = req.getParameter("categories");
			for(String i :strCates.split("-")) {
				cates.add(i);
			}
		}

		List<String> brands = new ArrayList<>();
		if(req.getParameter("brands")!=null && !req.getParameter("brands").isBlank()) {
			String strBrands = req.getParameter("brands");
			for(String i :strBrands.split("-")) {
				brands.add(i);
			}
		}

		int page;
		if(pageParam==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageParam );
		}


		boolean nextPageAva=false;
		boolean prvPageAva = false;
		List<ProductDto> productDtos;

		long pageCount = this.servicesImpl.getAllProductsCount();
		int cap = 10;

		int pages = (int) Math.ceil((int)(pageCount) /cap);




		if(page<=1) {
//			Here i applied filters on both prices and categories
//			if(pricesFilt.size()>0) {
//				if(cates.size()>0) {
//
//					productDtos = this.servicesImpl.getProducts2(page, pages, pricesFilt, cates);
//				}else {
//					productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
//				}
//
//			}
////			Here i applied filters on only categories
//
//			else if(cates.size()>0) {
//				productDtos=this.servicesImpl.getProducts2(1,cates,cap);
//			}
//			else {
//				productDtos=this.servicesImpl.getProducts2(1,cap);
//			}

			if(pricesFilt.isEmpty() ){
				if(cates.isEmpty()){
					if(brands.isEmpty()){
						// prices and cates and brands is empty
						productDtos=this.servicesImpl.getProducts2(1,cap);

					}else{
						// brands is not empty
						productDtos=this.servicesImpl.getProductsByBrands(1,brands,cap);
					}
				}else{
					if(!brands.isEmpty()){

						productDtos=this.servicesImpl.getProductsByBrands(1,brands,cap);

					}else{
						productDtos=this.servicesImpl.getProducts2(1,cates,cap);
					}
				}
			}else{
				if(!cates.isEmpty() && !brands.isEmpty()){
				// prices ,cates and brands are not empty
					productDtos = this.servicesImpl.getProducts2(1, cap, pricesFilt, cates,brands);
				}else if(!cates.isEmpty()){
				// prices and cates are not empty
					productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt,cates);

				}else if(!brands.isEmpty()){
					// prices and brands are not empty
					productDtos=this.servicesImpl.getProductsByPriceAndBrand(1,cap,pricesFilt,brands);
				}else{
					// cates and prices are not empty
					productDtos = this.servicesImpl.getProducts2(1, cap, pricesFilt);
				}

			}

			prvPageAva=false;
			nextPageAva=true;

		}

		else if(page>=pages) {
//			Here i applied filters on both prices and categories
//			if(pricesFilt.size()>0) {
//				if(cates.size()>0) {
//					productDtos = this.servicesImpl.getProducts2(page, pages, pricesFilt, cates);
//				}else {
//					productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
//				}
//
//			}
////			Here i applied filters on only categories
//
//			else if(cates.size()>0) {
//				productDtos=this.servicesImpl.getProducts2(1,cates,cap);
//			}
//			else {
//			productDtos=this.servicesImpl.getProducts2(pages,cap);
//			}

			if(pricesFilt.isEmpty() ){
				if(cates.isEmpty()){
					if(brands.isEmpty()){
						// prices and cates and brands is empty
						productDtos=this.servicesImpl.getProducts2(pages,cap);

					}else{
						// brands is not empty
						productDtos=this.servicesImpl.getProductsByBrands(pages,brands,cap);
					}
				}else{
					if(!brands.isEmpty()){

						productDtos=this.servicesImpl.getProductsByBrands(pages,brands,cap);

					}else{
						productDtos=this.servicesImpl.getProducts2(1,cates,cap);
					}
				}
			}else{
				if(!cates.isEmpty() && !brands.isEmpty()){
				// prices ,cates and brands are not empty
					productDtos = this.servicesImpl.getProducts2(pages, cap, pricesFilt, cates,brands);
				}else if(!cates.isEmpty()){
				// prices and cates are not empty
					productDtos=this.servicesImpl.getProducts2(pages,cap,pricesFilt,cates);

				}else if(!brands.isEmpty()){
					// prices and brands are not empty
					productDtos=this.servicesImpl.getProductsByPriceAndBrand(pages,cap,pricesFilt,brands);
				}else{
					// cates and prices are not empty
					productDtos = this.servicesImpl.getProducts2(pages, cap, pricesFilt);
				}

			}


			nextPageAva=false;
			prvPageAva = true;
		}
		else {
//			Here i applied filters on both prices and categories
//			if(pricesFilt.size()>0) {
//				if(cates.size()>0) {
//					productDtos = this.servicesImpl.getProducts2(page, pages, pricesFilt, cates);
//				}else {
//					productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
//				}
//
//			}
////			Here i applied filters on only categories
//
//			else if(cates.size()>0) {
//				productDtos=this.servicesImpl.getProducts2(1,cates,cap);
//			}
//			else {
//			productDtos=this.servicesImpl.getProducts2(page-1,cap);
//			}

			if(pricesFilt.isEmpty() ){
				if(cates.isEmpty()){
					if(brands.isEmpty()){
						// prices and cates and brands is empty
						productDtos=this.servicesImpl.getProducts2(page-1,cap);

					}else{
						// brands is not empty
						productDtos=this.servicesImpl.getProductsByBrands(page-1,brands,cap);
					}
				}else{
					if(!brands.isEmpty()){

						productDtos=this.servicesImpl.getProductsByBrands(page-1,brands,cap);

					}else{
						productDtos=this.servicesImpl.getProducts2(1,cates,cap);
					}
				}
			}else{
				if(!cates.isEmpty() && !brands.isEmpty()){
				// prices ,cates and brands are not empty
					productDtos = this.servicesImpl.getProducts2(page-1, cap, pricesFilt, cates,brands);
				}else if(!cates.isEmpty()){
				// prices and cates are not empty
					productDtos=this.servicesImpl.getProducts2(page-1,cap,pricesFilt,cates);

				}else if(!brands.isEmpty()){
					// prices and brands are not empty
					productDtos=this.servicesImpl.getProductsByPriceAndBrand(page-1,cap,pricesFilt,brands);
				}else{
					// cates and prices are not empty
					productDtos = this.servicesImpl.getProducts2(page-1, cap, pricesFilt);
				}

			}
			prvPageAva=true;
			nextPageAva=true;

		}

		if(stay) {
			HashMap hm = new HashMap();
			hm.put("prvPageAva", prvPageAva);
			hm.put("nextPageAva", nextPageAva);
			hm.put("products", productDtos);
			resp.getWriter().write(new Gson().toJson(hm));
		}else {
			req.setAttribute("brands", new Gson().toJson(this.servicesImpl.getBrands()));
			req.setAttribute("categories", new Gson().toJson(this.servicesImpl.getCategories()));
			req.setAttribute("prvPageAva", new Gson().toJson(prvPageAva));
			req.setAttribute("maxprice", this.servicesImpl.getMaxProductPrice());
			req.setAttribute("nextPageAva", new Gson().toJson(nextPageAva));
			req.setAttribute("products", new Gson().toJson(productDtos));
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Products.jsp");

			dispatcher.forward(req, resp);
		}


	}
}
