package test;

import java.util.List;

import org.hibernate.Hibernate;

import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.utils.DBUtils;

public class Test2 {
	public static void main(String[] args) {
//		System.out.println(DBUtils.getReviewsById(1).get());
//		DBUtils.saveJsonFileData();
//		System.out.println(DBUtils.getAllProducts());
		User u = DBUtils.getUserById(5).orElse(null);

		System.out.println(u);

//		Product product = DBUtils.getProductById(2).orElse(null);
//		System.out.println(product);

//		Cart c=DBUtils.insertInToCart(new CartTableDto(product, 1, new UserTableDto(u.getId(), u.getFname(), u.getLname(), u.getRole(),u.getEmail()))).orElse(null);
		List<Cart> c = DBUtils.getCartByUserId(u).orElse(null);
//		for(C)
		System.out.println(Hibernate.isInitialized(c.get(0).getUserId().getEmail()));
//		if(!Hibernate.isInitialized(c.get(0).getUserId().getAddress())) {
//			System.out.println("Initializing user address");
//
//		}
		System.out.println(c.get(0).getProducts().getImages());
	}
}
