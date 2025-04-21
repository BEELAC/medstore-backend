package com.beelac.medstorebackend.dao;

import java.util.List;

import com.beelac.medstorebackend.model.User;

public interface UserDao {
	void createUser(User user);

	User getUserById(int id);

	User getUserByUsername(String username);
	
	User getUserByEmail(String email);

	List<User> getAllUsers();

	void updateUser(User user);

	void deleteUser(int id);
}
