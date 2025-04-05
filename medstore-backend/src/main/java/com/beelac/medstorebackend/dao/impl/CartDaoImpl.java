package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.CartDao;
import com.beelac.medstorebackend.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Custom method to map result set to Cart
    private Cart mapRowToCart(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getInt("id"));
        cart.setUserId(rs.getInt("userId"));
        return cart;
    }

    @Override
    public Cart getCartByUserId(int userId) {
        String sql = "SELECT * FROM CART WHERE userId = ?";
        List<Cart> carts = jdbcTemplate.query(sql, this::mapRowToCart, userId);
        return carts.isEmpty() ? null : carts.get(0);  // Return the first cart for the user
    }

    @Override
    public void saveCart(Cart cart) {
        String sql = "INSERT INTO CART (userId) VALUES (?) ON DUPLICATE KEY UPDATE userId = ?";
        jdbcTemplate.update(sql, cart.getUserId(), cart.getUserId());
    }

    @Override
    public void deleteCart(int cartId) {
        String sql = "DELETE FROM CART WHERE id = ?";
        jdbcTemplate.update(sql, cartId);
    }
}