package dtos;

import com.vedant_servelets.entities.Product;

public class CartDto {
	private Product product;
	private long items;

	public CartDto() {

	}

	public CartDto(Product product, long items) {
		super();
		this.product = product;
		this.items = items;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getItems() {
		return items;
	}
	public void setItems(long items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CartDto [product=" + product + ", items=" + items + "]";
	}
	

}
