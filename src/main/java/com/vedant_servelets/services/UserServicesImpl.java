package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.User;
import com.vedant_servelets.utils.DBUtils;


public class UserServicesImpl implements UserServices {


	@Override
	public Optional<User> addUser(User u) {
		// TODO Auto-generated method stub

		return DBUtils.addUser(u);
	}


	@Override
	public Optional<User> getByUserId(long id) {
		// TODO Auto-generated method stub
		return DBUtils.getUserById(id);
	}


	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return DBUtils.getUserByEmail(email);
	}


	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		return DBUtils.getAllUsers();
	}


	@Override
	public Optional<User> updateUser(User u) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	@Override
	public Optional<User> deleteUserById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
