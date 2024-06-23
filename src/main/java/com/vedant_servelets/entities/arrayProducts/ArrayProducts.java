package com.vedant_servelets.entities.arrayProducts;

import java.util.ArrayList;

import com.vedant_servelets.entities.Product;

public class ArrayProducts {
	private ArrayList<Product> products;



	public ArrayProducts() {
		super();
	}



	public ArrayProducts(ArrayList<Product> products) {
		super();
		this.products = products;
	}



	public ArrayList<Product> getProducts() {
		return products;
	}



	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}




	@Override
	public String toString() {
		return "ArrayProducts [products=" + products + "]";
	}


}
