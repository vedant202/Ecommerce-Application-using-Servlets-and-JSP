package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
//F:\Servelet_JSP_Tuts\FilterTuts\src\main\java\test\test.json
public class Test1 {
	private static final String path = "F://Servelet_JSP_Tuts//FilterTuts//src//main//java//test//test.json";

	public static void main(String[] args) {
		System.out.println("Hii");
		Product p1 = new Product("a",100);
		Product p2 = new Product("a",100);

		ArrayList<Product> list = new ArrayList<>();
		list.add(p1);

		list.add(p2);
		ArrayProducts arrayProducts = new ArrayProducts(list);

		Gson gson = new Gson();
		try {
			System.out.println(arrayProducts);
			FileWriter writer = new FileWriter(path);
			gson.toJson(arrayProducts, writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
