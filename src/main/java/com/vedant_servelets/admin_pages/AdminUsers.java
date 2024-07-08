package com.vedant_servelets.admin_pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.vedant_servelets.entities.User;
import com.vedant_servelets.services.UserServices;
import com.vedant_servelets.services.UserServicesImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/users")
public class AdminUsers extends HttpServlet {
	private UserServices userServices;
	public AdminUsers() {
		// TODO Auto-generated constructor stub
		this.userServices = new UserServicesImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<User> users= this.userServices.getAllUsers().orElse(null);
		List<HashMap> al=new ArrayList<>();
		if(users!=null) {
			users.forEach(i->{
				System.out.println(i.getEmail());
				System.out.println(i.getAddress().getAddress1());
				System.out.println(i.getAddress().getCity());
				;
				HashMap hm = new HashMap();
				hm.put("id",Long.toString(i.getId()));
				hm.put("name", i.getFname().concat(" "+i.getLname()));
				hm.put("email", i.getEmail());
				hm.put("address", i.getAddress().getAddress1());
				hm.put("city",i.getAddress().getCity());
				hm.put("state", i.getAddress().getState());
				hm.put("phone",i.getPhoneNumber());
				hm.put("role", i.getRole());
				List<HashMap<String, String>> prods = new ArrayList<>();
				i.getCarts().forEach(p->{
					HashMap<String , String> pm = new HashMap<>();
					pm.put("pid",Long.toString(p.getProducts().getId()));
					pm.put("title", p.getProducts().getTitle());
					pm.put("price", Double.toString(p.getProducts().getPrice()));
					prods.add(pm);
				});
				hm.put("cart",prods);
				al.add(hm);
			});
		}
		req.setAttribute("users", new Gson().toJson(al));
		RequestDispatcher dispatch= req.getRequestDispatcher("/WEB-INF/AdminUsers.jsp");
		dispatch.forward(req, resp);
	}
}
