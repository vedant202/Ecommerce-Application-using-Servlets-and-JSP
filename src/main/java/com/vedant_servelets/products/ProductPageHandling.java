package com.vedant_servelets.products;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.vedant_servelets.services.ProductsServicesImpl;

import dtos.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/product/page")
public class ProductPageHandling extends HttpServlet {
	private Logger log=LogManager.getLogger(ProductPageHandling.class);
	private ProductsServicesImpl servicesImpl ;
	public ProductPageHandling() {
		// TODO Auto-generated constructor stub
		this.servicesImpl = new ProductsServicesImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pageParam = req.getParameter("p");
		System.out.println("pageParam :- "+pageParam);
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
			productDtos=this.servicesImpl.getProducts2(page,cap);
			prvPageAva=true;
			nextPageAva=true;

		}
		
		HashMap hm = new HashMap();
		
		hm.put("prvPageAva", prvPageAva);
		hm.put("nextPageAva", nextPageAva);
		hm.put("products", productDtos);

//		req.setAttribute("prvPageAva", new Gson().toJson(prvPageAva));
//
//		req.setAttribute("nextPageAva", new Gson().toJson(nextPageAva));
//		req.setAttribute("products", new Gson().toJson(productDtos));
		resp.getWriter().write(new Gson().toJson(hm));

	}

}
