package dtos;

import java.util.List;

import com.vedant_servelets.entities.Dimensions;
import com.vedant_servelets.entities.Reviews;



public class ProductDto {
	private Long id;
	private String title;
	private String description;
	private String category;
	private double price;
	private double discountPercentage;
	private double rating;
	private int stock;
	private List<String> tags;
	private String brand;
	private String sku;
	private double weight;
	private Dimensions dimensions;
	private String warrantyInformation;
	private String shippingInformation;
	private String availabilityStatus;

	private List<Reviews> reviews;
	private String returnPolicy;
	private String minimumOrderQuantity;
	
	private List<String> images;
	
	
	
	
	public ProductDto() {
		super();
	}



	public ProductDto(Long id, String title, String description, String category, double price,
			double discountPercentage, double rating, int stock, List<String> tags, String brand, String sku,
			double weight, Dimensions dimensions, String warrantyInformation, String shippingInformation,
			String availabilityStatus, List<Reviews> reviews, String returnPolicy, String minimumOrderQuantity,
			List<String> images) {
		super();
		this.id = id;
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



	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", discountPercentage=" + discountPercentage + ", rating=" + rating + ", stock="
				+ stock + ", tags=" + tags + ", brand=" + brand + ", sku=" + sku + ", weight=" + weight
				+ ", dimensions=" + dimensions + ", warrantyInformation=" + warrantyInformation
				+ ", shippingInformation=" + shippingInformation + ", availabilityStatus=" + availabilityStatus
				+ ", reviews=" + reviews + ", returnPolicy=" + returnPolicy + ", minimumOrderQuantity="
				+ minimumOrderQuantity + ", images=" + images + "]";
	}
	
	
}
