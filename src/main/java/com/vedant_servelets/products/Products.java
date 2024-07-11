package com.vedant_servelets.products;

import java.io.IOException;
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
			productDtos=this.servicesImpl.getProducts2(1,cap);
			prvPageAva=false;
			nextPageAva=true;

		}

		else if(page>=pages) {
			productDtos=this.servicesImpl.getProducts2(pages,cap);
			nextPageAva=false;
			prvPageAva = true;
		}
		else {
			productDtos=this.servicesImpl.getProducts2(page-1,cap);
			prvPageAva=true;
			nextPageAva=true;

		}



		req.setAttribute("prvPageAva", new Gson().toJson(prvPageAva));

		req.setAttribute("nextPageAva", new Gson().toJson(nextPageAva));
		req.setAttribute("products", new Gson().toJson(productDtos));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Products.jsp");

		dispatcher.forward(req, resp);
	}
}
