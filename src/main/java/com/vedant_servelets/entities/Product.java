package com.vedant_servelets.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;


@Entity
public class Product implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String category;
	private double price;
	private double discountPercentage;
	private double rating;
	private int stock;

	@Basic
	private Timestamp createdAt;

	private List<String> tags;
	private String brand;
	private String sku;
	private double weight;
	@OneToOne(cascade = CascadeType.ALL)
	private Dimensions dimensions;
	private String warrantyInformation;
	private String shippingInformation;
	private String availabilityStatus;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Reviews> reviews;
	private String returnPolicy;
	private String minimumOrderQuantity;
	@ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "image_id"))
    @Column(name = "images")
	@OrderColumn(name = "arrangement_index",insertable = true)
	private List<String> images;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "products")

	private List<Cart> cart;



	public Product() {
		super();
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());

	}

	public Product(String title, String description, String category, double price, double discountPercentage,
			double rating, int stock, List<String> tags, String brand, String sku, double weight, Dimensions dimensions,
			String warrantyInformation, String shippingInformation, String availabilityStatus, List<Reviews> reviews,
			String returnPolicy, String minimumOrderQuantity, List<String> images) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.rating = rating;
		this.stock = stock;
		this.tags = tags;
		this.brand = brand;
		this.sku = sku;
		this.weight = weight;
		this.dimensions = dimensions;
		this.warrantyInformation = warrantyInformation;
		this.shippingInformation = shippingInformation;
		this.availabilityStatus = availabilityStatus;
		this.reviews = reviews;
		this.returnPolicy = returnPolicy;
		this.minimumOrderQuantity = minimumOrderQuantity;
		this.images = images;
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());

	}


	public Product(String title, String description, String category, double price, double discountPercentage,
			double rating, int stock, List<String> tags, String brand, String sku, double weight, Dimensions dimensions,
			String warrantyInformation, String shippingInformation, String availabilityStatus, List<Reviews> reviews,
			String returnPolicy, String minimumOrderQuantity, List<String> images, List<Cart> cart) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.rating = rating;
		this.stock = stock;
		this.tags = tags;
		this.brand = brand;
		this.sku = sku;
		this.weight = weight;
		this.dimensions = dimensions;
		this.warrantyInformation = warrantyInformation;
		this.shippingInformation = shippingInformation;
		this.availabilityStatus = availabilityStatus;
		this.reviews = reviews;
		this.returnPolicy = returnPolicy;
		this.minimumOrderQuantity = minimumOrderQuantity;
		this.images = images;
		this.cart = cart;
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());

	}



	public Product(Long id, String title, String description, String category, double price, double discountPercentage,
			double rating, int stock, List<String> tags, String brand, String sku, double weight,
			Dimensions dimensions, String warrantyInformation, String shippingInformation, String availabilityStatus,
			List<Reviews> reviews, String returnPolicy, String minimumOrderQuantity, List<String> images,
			List<Cart> cart) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.rating = rating;
		this.stock = stock;
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());
		this.tags = tags;
		this.brand = brand;
		this.sku = sku;
		this.weight = weight;
		this.dimensions = dimensions;
		this.warrantyInformation = warrantyInformation;
		this.shippingInformation = shippingInformation;
		this.availabilityStatus = availabilityStatus;
		this.reviews = reviews;
		this.returnPolicy = returnPolicy;
		this.minimumOrderQuantity = minimumOrderQuantity;
		this.images = images;
		this.cart = cart;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Dimensions getDimensions() {
		return dimensions;
	}
	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	public String getWarrantyInformation() {
		return warrantyInformation;
	}
	public void setWarrantyInformation(String warrantyInformation) {
		this.warrantyInformation = warrantyInformation;
	}
	public String getShippingInformation() {
		return shippingInformation;
	}
	public void setShippingInformation(String shippingInformation) {
		this.shippingInformation = shippingInformation;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public List<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
	public String getReturnPolicy() {
		return returnPolicy;
	}
	public void setReturnPolicy(String returnPolicy) {
		this.returnPolicy = returnPolicy;
	}
	public String getMinimumOrderQuantity() {
		return minimumOrderQuantity;
	}
	public void setMinimumOrderQuantity(String minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}


	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", discountPercentage=" + discountPercentage + ", rating=" + rating + ", stock="
				+ stock + ", createdAt=" + createdAt + ", tags=" + tags + ", brand=" + brand + ", sku=" + sku
				+ ", weight=" + weight + ", dimensions=" + dimensions + ", warrantyInformation=" + warrantyInformation
				+ ", shippingInformation=" + shippingInformation + ", availabilityStatus=" + availabilityStatus
				+ ", reviews=" + reviews + ", returnPolicy=" + returnPolicy + ", minimumOrderQuantity="
				+ minimumOrderQuantity + ", images=" + images + ", cart=" + cart + "]";
	}





}
