package dtos;


public class CartDto {
	private long id;
	private ProductDto products;
	private long items;

	public CartDto() {

	}

	public CartDto(ProductDto products, long items) {
		super();
		this.products = products;
		this.items = items;
	}
	
	public CartDto(long id,ProductDto products, long items) {
		this.id=id;
		this.products = products;
		this.items = items;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductDto getProductDto() {
		return products;
	}
	public void setProductDto(ProductDto ProductDtos) {
		this.products = ProductDtos;
	}
	public long getItems() {
		return items;
	}
	public void setItems(long items) {
		this.items = items;
	}


	@Override
	public String toString() {
		return "CartDto [ProductDto=" + products + ", items=" + items + "]";
	}


}
