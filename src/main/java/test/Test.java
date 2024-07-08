package test;

import java.util.List;

import com.vedant_servelets.utils.DBUtils;

public class Test {
	public static void main(String[] args) {
		List<com.vedant_servelets.entities.Product> products=DBUtils.getAllProductsSortByCreatedAtAndCategory().get();
		products.forEach(i->{
			System.out.println("Title :- "+ i.getTitle()+" createdAt :- "+i.getCreatedAt()+" images :- "+i.getImages());
		});
	}
}
