package com.vedant_servelets.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.vedant_servelets.entities.Address;
import com.vedant_servelets.entities.Cart;
import com.vedant_servelets.entities.Dimensions;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.Reviews;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;

import dtos.CartTableDto;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DBUtils {
	private static Logger log = LogManager.getLogger(DBUtils.class);

	public static void saveListOfProduct(Session session, ArrayList<Product> products) {
		Transaction transaction = session.beginTransaction();

		try {
			products.stream().forEach(i -> {
				System.out.println("Product Type id:- " + i.getId());
				i.setId(null);
				session.persist(i);
			});

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
		transaction.commit();

	}

	static SessionFactory getSessionFactory() {

		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Dimensions.class);
		configuration.addAnnotatedClass(Reviews.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Address.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Cart.class);
		SessionFactory factory = configuration.buildSessionFactory();
//		SessionFactory factory = new Configuration().addAnnotatedClass(Product.class).addAnnotatedClass(Reviews.class)
//				.addAnnotatedClass(Dimension.class).buildSessionFactory();
		return factory;
	}

	public static void saveJsonFileData() {
		JsonFileHandling fileHandling = new JsonFileHandling();
		ArrayProducts products = fileHandling.getDataFromFile();

		SessionFactory factory = getSessionFactory();

		String hql = "from Product";

		try {
			factory.inSession((Session t) -> {
				Optional<List<Product>> list = null;
				list = Optional.ofNullable(t.createSelectionQuery(hql, Product.class).getResultList().size() > 0
						? t.createSelectionQuery(hql, Product.class).getResultList()
						: null);
				System.out.println("List of products :- " + list + " list.isEmpty():- " + list.isEmpty());
				if (list.isEmpty()) {
					list = Optional.of(products.getProducts());
					System.out.println("Addingproducts in database" + list);
					saveListOfProduct(t, (ArrayList<Product>) list.get());

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

	public static Optional<List<Product>> getAllProducts() {
		Optional<List<Product>> products = null;
		Session session = getSessionFactory().openSession();
		log.info("Getting all products from DB");
		try {
			JpaCriteriaQuery<Product> query = session.getCriteriaBuilder().createQuery(Product.class);
			var productRoot = query.from(Product.class);
			query.select(productRoot);
			List<Product> listProds = session.createSelectionQuery(query).getResultList();
			products = Optional.ofNullable(listProds.size() > 0 ? listProds : null);

			if (!products.isEmpty()) {
				products.get().forEach(i -> {
					Hibernate.initialize(i.getReviews());
					Hibernate.initialize(i.getImages());
					Hibernate.initialize(i.getCart());
					for (Cart c : i.getCart()) {
						Hibernate.initialize(c.getProducts());
						Hibernate.initialize(c.getProducts().getReviews());
						Hibernate.initialize(c.getProducts().getImages());
					}

				});
			}
			log.info("Seccessfully retreve all products from database");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}

		return products;

	}

	public static Optional<Product> getProductById(long id) {
		Session session = getSessionFactory().openSession();
		Optional<Product> product = null;

		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
			Root<Product> productRoot = query.from(Product.class);
			query.select(productRoot);
			query.where(criteriaBuilder.equal(productRoot.get("id"), id));
			product = Optional.ofNullable(session.createQuery(query).getSingleResult());
			if (!product.isEmpty()) {
				System.out.println("getProductById here product is not empty");
				Hibernate.initialize(product.get().getReviews());
				Hibernate.initialize(product.get().getImages());
				Hibernate.initialize(product.get().getCart());
				for (Cart c : product.get().getCart()) {
					Hibernate.initialize(c.getUserId());
					Hibernate.initialize(c.getUserId().getAddress());

				}

				System.out.println("Product id:- " + product.get().getId());
				System.out.println("Product title:- " + product.get().getTitle());
				System.out.println("Product reviews:- " + product.get().getReviews());

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(product);
		return product;
	}

	// Get product without user and cart information
	public static Optional<Product> getProductById2(long id) {
		Session session = getSessionFactory().openSession();
		Optional<Product> product = null;

		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
			Root<Product> productRoot = query.from(Product.class);
			query.select(productRoot);
			query.where(criteriaBuilder.equal(productRoot.get("id"), id));
			product = Optional.ofNullable(session.createQuery(query).getSingleResult());
			if (!product.isEmpty()) {
				System.out.println("getProductById here product is not empty");
				Hibernate.initialize(product.get().getReviews());
				Hibernate.initialize(product.get().getImages());


				System.out.println("Product id:- " + product.get().getId());
				System.out.println("Product title:- " + product.get().getTitle());
				System.out.println("Product reviews:- " + product.get().getReviews());

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return product;
	}

	public static Optional<User> getUserByEmail(String email) {
		Session session = getSessionFactory().openSession();
		Optional<User> user = null;

		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			query.where(criteriaBuilder.equal(root.get("email"), email));

			user = Optional.ofNullable(session.createQuery(query).getSingleResult());
			if (!user.isEmpty()) {
				Hibernate.initialize(user.get().getAddress());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return user;
	}

	public static Optional<Reviews> getReviewsById(long id) {
		Session session = getSessionFactory().openSession();
		Optional<Reviews> review = null;
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Reviews> criteriaQuery = criteriaBuilder.createQuery(Reviews.class);
			Root<Reviews> rootReview = criteriaQuery.from(Reviews.class);
			criteriaQuery.select(rootReview);
			criteriaQuery.where(criteriaBuilder.equal(rootReview.get("reviewsId"), id));

			review = Optional.ofNullable(session.createQuery(criteriaQuery).getSingleResultOrNull());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return review;
	}

	public static Optional<List<Reviews>> getAllReviews() {
		Session session = getSessionFactory().openSession();
		Optional<List<Reviews>> reviews = null;
		try {
			CriteriaQuery<Reviews> criteriaQuery = session.getCriteriaBuilder().createQuery(Reviews.class);
			Root<Reviews> root = criteriaQuery.from(Reviews.class);
			criteriaQuery.select(root);

			List<Reviews> listReviews = session.createSelectionQuery(criteriaQuery).getResultList();
			reviews = Optional.ofNullable(listReviews.size() > 0 ? listReviews : null);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return reviews;
	}

	public static Optional<List<User>> getAllUsers() {
		Session session = getSessionFactory().openSession();
		Optional<List<User>> users = null;
		try {
			CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
			users = Optional.ofNullable(
					session.createSelectionQuery(criteriaQuery.select(criteriaQuery.from(User.class))).getResultList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	public static Optional<User> getUserById(long id) {
		Session session = getSessionFactory().openSession();
		Optional<User> user = null;

		try {
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			query.where(criteriaBuilder.equal(root.get("id"), id));

			try {
				user = Optional.ofNullable(session.createQuery(query).getSingleResult());
				if (user != null || !user.isEmpty()) {
					Hibernate.initialize(user.get().getAddress());
				}
			} catch (NoResultException e) {
				// TODO: handle exception
				e.printStackTrace();
				user = Optional.empty();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return user;

	}

	public static Optional<User> addUser(User u) {

		Session session = getSessionFactory().openSession();
		boolean usersaved = false;

		try {
			session.beginTransaction();
			session.persist(u);
			usersaved = true;

		} catch (Exception e) {
			// TODO: handle exception
			usersaved = false;
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		if (!usersaved) {
			return Optional.empty();
		}
		return Optional.ofNullable(u);
	}

	public static Optional<List<Cart>> getCartByUserId(User userId) {
		Session session = getSessionFactory().openSession();
		Optional<List<Cart>> carts = null;

		try {

			try {
				carts = Optional.ofNullable(session.createQuery(
						"SELECT DISTINCT c FROM Cart c LEFT JOIN FETCH c.products p LEFT JOIN FETCH p.images LEFT JOIN FETCH p.reviews LEFT JOIN FETCH c.userId u WHERE u.id = :userId")
						.setParameter("userId", userId.getId()).getResultList());

			} catch (NoResultException e) {
				// TODO: handle exception
				log.error(e.getMessage());
				return Optional.empty();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return carts;

	}

	public static Optional<List<Cart>> getAllCart() {
		Session session = getSessionFactory().openSession();
		Optional<List<Cart>> cart = null;
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Cart> query = criteriaBuilder.createQuery(Cart.class);
			Root<Cart> cartRoot = query.from(Cart.class);
			query.select(cartRoot);

			cart = Optional.ofNullable(session.createSelectionQuery(query).getResultList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cart;
	}

	public static Optional<Cart> insertInToCart(CartTableDto c) {
		System.out.println("checking CartTableDto " + c);
		Session session = getSessionFactory().openSession();
		Optional<Cart> cart = null;
		session.beginTransaction();
		try {
//			cart = getCartByUserId(getUserById(c.getUser().getId()).orElse(null));
//			//Checking if user is present in Cart if user is present add product or else add new cart with user
//			if(cart!=null) {
//				if(cart.isPresent()) {
//					cart.get().getProducts().add(c.getProduct());
//					cart.get().setProducts(cart.get().getProducts());
//					session.merge(cart.get());
//				}
//			}
//			else {
//				List<Product> products = new ArrayList<>();
//				products.add(c.getProduct());
			User users = null;
//			List<Product> products = null;
//			if (cart != null) {
//				products = cart.get().getProducts();
//			}
//
//			if(products==null) {
//				products = new ArrayList<Product>();
//			}

//			products.add(c.getProduct());
			cart = Optional.ofNullable(new Cart(getUserById(c.getUser().getId()).get(), c.getProduct(), c.getItems()));

			session.merge(cart.get());

//			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {

			session.close();
		}

		return cart;
	}

	public static Optional<Cart> getCartById(long id) {

		Session session = getSessionFactory().openSession();

		try {
			String hql = "SELECT c FROM Cart c LEFT JOIN FETCH c.products p LEFT JOIN FETCH p.reviews LEFT JOIN FETCH p.images where c.id=:id";
			Optional<Cart> c = Optional.of(session.createQuery(hql, Cart.class).setParameter("id", id).getSingleResult());
			return c;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}

		return Optional.empty();
	}

	public static Optional<Cart> increaseCartItems(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		try {


			Optional<Cart> c = getCartById(id);
			if (c != null) {
				if (!c.isEmpty()) {
					String hql = "Update Cart set items=:items where id=:id";
					int affectedEntity = session.createMutationQuery(hql).setParameter("id", c.get().getId())
							.setParameter("items", c.get().getItems() + 1).executeUpdate();
					session.getTransaction().commit();
					if (affectedEntity > 0) {
						Optional<Cart> updateCart = getCartById(id);
						return updateCart;
					}
				}
			}

			String hql = "";
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {

			session.close();
		}

		return Optional.empty();
	}

	public static Optional<Cart> decreaseCartItems(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		try {


			Optional<Cart> c = getCartById(id);
			if (c != null) {
				if (!c.isEmpty()) {
					String hql = "Update Cart set items=:items where id=:id";
					int affectedEntity = session.createMutationQuery(hql).setParameter("id", c.get().getId())
							.setParameter("items", c.get().getItems()>0?c.get().getItems() - 1:0).executeUpdate();
					session.getTransaction().commit();
					if (affectedEntity > 0) {
						Optional<Cart> updateCart = getCartById(id);
						return updateCart;
					}
				}
			}

			String hql = "";
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {

			session.close();
		}

		return Optional.empty();
	}

	public static boolean removeCart(long id) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		try {

			String hql = "Delete from Cart where id=:id";
			int affectedEntities = session.createMutationQuery(hql).setParameter("id", id).executeUpdate();
			System.out.println("delete query affected Entities :- " + affectedEntities);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return false;
	}

	public static Product saveProduct(Product product) {
		Session session = getSessionFactory().openSession();


		try {
			session.beginTransaction();
			session.persist(product);
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		return product;
	}

	// Update the product with product id
	public static Optional<Product> updateProduct(Product product) {
		Product checkProduct=getProductById2(product.getId()).orElse(null);
		Session session = getSessionFactory().openSession();
		if(checkProduct!=null) {
			checkProduct.setAvailabilityStatus(product.getAvailabilityStatus());
			checkProduct.setBrand(product.getBrand());
			checkProduct.setCategory(product.getCategory());
			checkProduct.setDescription(product.getDescription());
			checkProduct.setDiscountPercentage(product.getDiscountPercentage());
			checkProduct.setImages(product.getImages());
			checkProduct.setMinimumOrderQuantity(product.getMinimumOrderQuantity());
			checkProduct.setPrice(product.getPrice());
			checkProduct.setRating(product.getRating());
			checkProduct.setReturnPolicy(product.getReturnPolicy());
			checkProduct.setShippingInformation(product.getShippingInformation());
			checkProduct.setSku(product.getSku());
			checkProduct.setStock(product.getStock());
			checkProduct.setTags(product.getTags());
			checkProduct.setTitle(product.getTitle());
			checkProduct.setWarrantyInformation(product.getWarrantyInformation());
			checkProduct.setWeight(product.getWeight());

			try {
				session.beginTransaction();
				session.merge(checkProduct);
				session.getTransaction().commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				session.close();
			}

			return Optional.ofNullable(checkProduct);
		}

		return Optional.empty();
	}

	public static void deleteProductById(long id) {

		Product checkProduct=getProductById2(id).orElse(null);

		if(checkProduct!=null) {
			Session session=getSessionFactory().openSession();

			try {
				session.beginTransaction();
				session.remove(checkProduct);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				session.getTransaction().rollback();
				e.printStackTrace();

			}
			finally {
				session.close();
			}
		}else {
			throw new RuntimeException("Product is not present with id :- "+id);
		}


	}
}
