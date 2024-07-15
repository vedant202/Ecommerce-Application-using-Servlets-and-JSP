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
		List<Integer> pricesFilt = new ArrayList<Integer>();
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
			if(pricesFilt.size()>0) {
				productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
			}else {
				productDtos=this.servicesImpl.getProducts2(1,cap);
			}
			
			prvPageAva=false;
			nextPageAva=true;

		}

		else if(page>=pages) {
			if(pricesFilt.size()>0) {
				productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
			}else {
			productDtos=this.servicesImpl.getProducts2(pages,cap);
			}
			nextPageAva=false;
			prvPageAva = true;
		}
		else {
			if(pricesFilt.size()>0) {
				productDtos=this.servicesImpl.getProducts2(1,cap,pricesFilt);
			}else {
			productDtos=this.servicesImpl.getProducts2(page-1,cap);
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
			req.setAttribute("prvPageAva", new Gson().toJson(prvPageAva));
			req.setAttribute("maxprice", this.servicesImpl.getMaxProductPrice());
			req.setAttribute("nextPageAva", new Gson().toJson(nextPageAva));
			req.setAttribute("products", new Gson().toJson(productDtos));
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Products.jsp");

			dispatcher.forward(req, resp);
		}

		
	}
}
