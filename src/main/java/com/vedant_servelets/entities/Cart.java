package com.vedant_servelets.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Cart implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userId;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Product products;

	private long items;




	public Cart() {
		super();
	}


	public Cart(User userId, Product products, long items) {
		super();
		this.userId = userId;
		this.products = products;
		this.items = items;
	}



	public Cart(long id, User userId, Product products, long items) {
		super();
		this.id = id;
		this.userId = userId;
		this.products = products;
		this.items = items;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public User getUserId() {
		return userId;
	}



	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Product getProducts() {
		return products;
	}


	public void setProducts(Product products) {
		this.products = products;
	}


	public long getItems() {
		return items;
	}



	public void setItems(long items) {
		this.items = items;
	}




	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + products + ", items=" + items + "]";
	}





}
