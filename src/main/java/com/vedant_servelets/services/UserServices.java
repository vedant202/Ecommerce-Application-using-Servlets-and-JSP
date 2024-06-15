package com.vedant_servelets.services;

import java.util.List;
import java.util.Optional;

import com.vedant_servelets.entities.User;

public interface UserServices {
	Optional<User> addUser(User u);
	Optional<User> getByUserId(long id);
	Optional<List<User>> getAllUsers();
	Optional<User> updateUser(User u);
	Optional<User> deleteUserById(long id);
	Optional<User> getUserByEmail(String email);
}
