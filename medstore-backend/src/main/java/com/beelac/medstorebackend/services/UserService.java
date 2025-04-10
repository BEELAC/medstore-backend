package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.User;
import java.util.List;

public interface UserService {
	User getUserById(int id);

	User getUserByUsername(String username);

	List<User> getAllUsers();

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(int id);
}
