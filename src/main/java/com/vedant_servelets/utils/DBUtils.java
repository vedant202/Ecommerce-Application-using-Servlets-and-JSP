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
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.vedant_servelets.entities.Dimensions;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.Reviews;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DBUtils {
	private static Logger log=LogManager.getLogger(DBUtils.class);
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
				list = Optional.ofNullable(t.createSelectionQuery(hql, Product.class).
						getResultList().size() > 0 ? t.createSelectionQuery(hql, Product.class).getResultList()
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
		Session session =  getSessionFactory().openSession();
		log.info("Getting all products from DB");
		try {
			JpaCriteriaQuery<Product> query =session.getCriteriaBuilder().createQuery(Product.class);
			var productRoot = query.from(Product.class);
			query.select(productRoot);
			List<Product> listProds = session.createSelectionQuery(query).getResultList();
			products=Optional.ofNullable(listProds.size()>0?listProds:null);
			
			if(!products.isEmpty()) {
				products.get().forEach(i->{
					Hibernate.initialize(i.getReviews());
					Hibernate.initialize(i.getImages());
				});
			}
			log.info("Seccessfully retreve all products from database");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return products;
		
		
	}
	
	public static Optional<Product> getProductById(long id) {
		Session session =  getSessionFactory().openSession();
		Optional<Product> product = null;
		
		try {
			CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			CriteriaQuery<Product> query=criteriaBuilder.createQuery(Product.class);
			Root<Product> productRoot =query.from(Product.class);
			query.select(productRoot);
			query.where(criteriaBuilder.equal(productRoot.get("id"), id));
			product=Optional.ofNullable(session.createQuery(query).getSingleResult());
			if(!product.isEmpty()) {
				System.out.println("getProductById here product is not empty");
				Hibernate.initialize(product.get().getReviews());
				Hibernate.initialize(product.get().getImages());

				System.out.println("Product id:- "+product.get().getId());
				System.out.println("Product title:- "+product.get().getTitle());
				System.out.println("Product reviews:- "+product.get().getReviews());

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(product);
		return product;
	}

	public static Optional<Reviews> getReviewsById(long id){
		Session session = getSessionFactory().openSession();
		Optional<Reviews> review = null;
		try {
			CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			CriteriaQuery<Reviews> criteriaQuery=criteriaBuilder.createQuery(Reviews.class);
			Root<Reviews> rootReview=criteriaQuery.from(Reviews.class);
			criteriaQuery.select(rootReview);
			criteriaQuery.where(criteriaBuilder.equal(rootReview.get("reviewsId"), id));
		
			review=Optional.ofNullable(session.createQuery(criteriaQuery).getSingleResultOrNull());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return review;
	}
	
	
	public static Optional<List<Reviews>> getAllReviews(){
		Session session=getSessionFactory().openSession();
		Optional<List<Reviews>> reviews=null;
		try {
			CriteriaQuery<Reviews> criteriaQuery=session.getCriteriaBuilder().createQuery(Reviews.class);
			Root<Reviews> root=criteriaQuery.from(Reviews.class);
			criteriaQuery.select(root);
			
			List<Reviews> listReviews=session.createSelectionQuery(criteriaQuery).getResultList();
			reviews=Optional.ofNullable(listReviews.size()>0?listReviews:null);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return reviews;
	}
}
