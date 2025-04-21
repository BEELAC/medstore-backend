package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.UserDao;
import com.beelac.medstorebackend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    // Custom method to map result set to User
	private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
	    User user = new User();
	    user.setId(rs.getInt("id"));
	    user.setUsername(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    user.setName(rs.getString("name"));
	    user.setEmail(rs.getString("email"));
	    user.setAddress(rs.getString("address"));
	    user.setCity(rs.getString("city"));
	    user.setCreditCard(rs.getString("credit_card"));
	    user.setPhone(rs.getString("phone"));
	    user.setCreatedOn(rs.getTimestamp("created_on"));
	    user.setModifiedOn(rs.getTimestamp("modified_on"));
	    user.setIsAdmin(rs.getBoolean("isAdmin"));
	    return user;
	}
	
	@Override
	public void createUser(User user) {
		String sql = "INSERT INTO USER (username, password, name, email, address, city, credit_card, phone, isAdmin) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getAddress(),
				user.getCity(),
				user.getCreditCard(),
				user.getPhone(),
				user.isAdmin()
		);
	}

	@Override
	public User getUserById(int id) {
	    String sql = "SELECT * FROM USER WHERE id = ?";
	    return jdbcTemplate.queryForObject(sql, this::mapRowToUser, id);
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM USER WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, this::mapRowToUser, username);
	}
	
	@Override
	public User getUserByEmail(String email) {
	    String sql = "SELECT * FROM USER WHERE email = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setIsAdmin(rs.getBoolean("isAdmin"));
	            return user;
	        }, email);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM USER";
		return jdbcTemplate.query(sql, this::mapRowToUser);
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE USER SET password = ?, name = ?, email = ?, address = ?, city = ?, credit_card = ?, phone = ?, isAdmin = ? " +
                "WHERE id = ?";
		jdbcTemplate.update(sql,
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getAddress(),
				user.getCity(),
				user.getCreditCard(),
				user.getPhone(),
				user.isAdmin(),
				user.getId()
		);
	}

	@Override
	public void deleteUser(int id) {
        String sql = "DELETE FROM USER WHERE id = ?";
        jdbcTemplate.update(sql, id);
	}
}
