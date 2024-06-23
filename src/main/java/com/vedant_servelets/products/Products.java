package com.vedant_servelets.products;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.apis.GettingProductsDetails;
import com.vedant_servelets.apis.GettingProductsImpl;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Products extends HttpServlet {

	private Logger log=LogManager.getLogger(Products.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		HttpSession session = req.getSession(false);

		String user_email=(String)session.getAttribute("user");
		log.info(String.format("Request to /products has been by %s",user_email));
		String[] arr=StringUtils.split(user_email,'@');
		String userName = arr[0];

		req.setAttribute("username", userName);
		req.setAttribute("products", new Gson().toJson(products.getProducts()));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Products.jsp");

		dispatcher.forward(req, resp);
	}
}
