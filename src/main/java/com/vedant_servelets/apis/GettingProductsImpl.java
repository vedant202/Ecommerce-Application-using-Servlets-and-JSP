package com.vedant_servelets.apis;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

import com.google.gson.Gson;
import com.vedant_servelets.entities.Product;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.filehandling.JsonFileHandling;


public class GettingProductsImpl implements GettingProductsDetails {
	private final String productApi = "https://dummyjson.com/products/1";
	private final String productsApi = "https://dummyjson.com/products";
	DefaultAsyncHttpClientConfig.Builder builder = Dsl.config();
	AsyncHttpClient client =Dsl.asyncHttpClient(builder);
	private Logger log = LogManager.getLogger(GettingProductsImpl.class);




	@Override
	public ArrayProducts getAllProducts() {
		ArrayProducts products = null;
		System.out.println("Getting all products from third party");
		log.info("Getting products  data from :- "+productsApi);
		Future<Response> f= client.prepareGet(productsApi).addHeader("Content-Type", "application/json").execute(new AsyncCompletionHandler<Response>() {


			@Override
			public Response onCompleted(Response response) throws Exception {
				// TODO Auto-generated method stub
				return response;
			}
		});

		try {
			log.info("Getting products response body  data from :- "+productsApi);
			String jsonString = f.get().getResponseBody();

			log.info("Converting json string to object");

			System.out.println(jsonString);
//			Type type=new TypeToken<ArrayList<Product>>() {}.getType();
			products = new Gson().fromJson(jsonString, ArrayProducts.class);
			System.out.println("Size of Products :- "+products);
			JsonFileHandling fileHandling = new JsonFileHandling();
			fileHandling.saveData(products);

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return products;
	}




	@Override
	public Product getProduct() {
		Product product=null;

//		Request request =Dsl.get(productsApi).addHeader("Content-Type", "application/json").build();
//		ListenableFuture<Response> listenableFuture=client.executeRequest(request);
//		try {
//			System.out.println("In synchronus request");
//			Response responseFuture=listenableFuture.get();
//			System.out.println(responseFuture.getStatusCode());
//			System.out.println(responseFuture.getResponseBody());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		log.info("Getting product  data from :- "+productApi);

		Future<Response> f=client.prepareGet(productApi).addHeader("Content-Type", "application/json").execute(new AsyncCompletionHandler<Response>() {


			@Override
			public Response onCompleted(Response response) throws Exception {
				// TODO Auto-generated method stub
				return response;
			}
		});

		try {
			Integer status=f.get().getStatusCode();
			System.out.println("Done :- "+f.isDone()+" cancelled :- "+f.isCancelled());
			System.out.println(status);
			String jsonString = f.get().getResponseBody();
			System.out.println(jsonString);




			product = new Gson().fromJson(jsonString, Product.class);
			System.out.println(product);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		try {

			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

}
