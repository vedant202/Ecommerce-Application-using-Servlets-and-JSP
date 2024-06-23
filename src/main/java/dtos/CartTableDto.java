package dtos;

import com.vedant_servelets.entities.Product;

public class CartTableDto {
	private Product product;
	private long items;
	private UserTableDto user;
	public CartTableDto(Product product, long items, UserTableDto user) {
		super();
		this.product = product;
		this.items = items;
		this.user = user;
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
	public UserTableDto getUser() {
		return user;
	}
	public void setUser(UserTableDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CartTableDto [product=" + product + ", items=" + items + ", user=" + user + "]";
	}



}
