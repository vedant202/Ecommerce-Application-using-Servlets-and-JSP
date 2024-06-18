package com.vedant_servelets.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private User userId;

	@ManyToOne
	private Product productId;

	private long items;




	public Cart() {
		super();
	}


	public Cart(User userId, Product productId, long items) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.items = items;
	}



	public Cart(long id, User userId, Product productId, long items) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
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



	public Product getProductId() {
		return productId;
	}



	public void setProductId(Product productId) {
		this.productId = productId;
	}



	public long getItems() {
		return items;
	}



	public void setItems(long items) {
		this.items = items;
	}



	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + productId + ", items=" + items + "]";
	}





}
