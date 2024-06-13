package com.vedant_servelets.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.vedant_servelets.entities.arrayProducts.ArrayProducts;
import com.vedant_servelets.exception.CustomFileException;

public class JsonFileHandling {
	private final String path = "F://Servelet_JSP_Tuts//FilterTuts//src//main//java//jsonFiles//Products.json";
	private Logger log=LogManager.getLogger(JsonFileHandling.class);

	public void saveData(ArrayProducts arrayProducts) {
		File file = new File(path);

		if(file.exists()) {
			System.out.println("Json file is present");
			Gson gson = new Gson();
			try {
				FileWriter writer = new FileWriter(file);
				gson.toJson(arrayProducts,writer);
				writer.close();

			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Json file is not present");
		}
	}

	public ArrayProducts getDataFromFile() {
		File file = new File(path);
		log.info(String.format("Getting data from file %s",path));
		ArrayProducts products=null;
		if(!file.exists()) {
			log.warn(String.format("File is not present making new file"));
			boolean createNewFile;
			try {

				createNewFile = file.createNewFile();
				log.info(String.format("New file created"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.info("Io exception file creation file :- "+e.getMessage());
				e.printStackTrace();
			}
			return null;

		}
		if(file.exists()) {
			Gson gson = new Gson();
			log.info(String.format("File is present"));
			try {
				products=gson.fromJson(new FileReader(file), ArrayProducts.class);
				System.out.println("Total item after querying from file got is :- "+products.getProducts().size());
				log.info("Total item after querying from file got is :- "+products.getProducts().size());
				log.info(String.format("Fetched Data from file"));
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				log.error(String.format("JsonSyntaxException or JsonIOException %s ",e.getMessage()));
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			log.error(String.format("File is not present"));
			throw new CustomFileException("File not found at path :- "+path);
		}
		return products;

	}

}
