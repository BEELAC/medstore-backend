package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.CartDetailsDao;
import com.beelac.medstorebackend.model.CartDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartDetailsDaoImpl implements CartDetailsDao {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;

    // Custom method to map result set to CartDetails
    private CartDetails mapRowToCartDetails(ResultSet rs) throws SQLException {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setId(rs.getInt("id"));
        cartDetails.setCartId(rs.getInt("cartId"));
        cartDetails.setProductId(rs.getInt("productId"));
        cartDetails.setQuantity(rs.getInt("quantity"));
        return cartDetails;
    }

    @Override
    public void addProductToCart(CartDetails cartDetails) {
        String sql = "INSERT INTO CART_DETAILS (cartId, productId, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, cartDetails.getCartId(), cartDetails.getProductId(), cartDetails.getQuantity());
    }

    @Override
    public void updateProductQuantity(int cartId, int productId, int quantity) {
        String sql = "UPDATE CART_DETAILS SET quantity = ? WHERE cartId = ? AND productId = ?";
        jdbcTemplate.update(sql, quantity, cartId, productId);
    }

    @Override
    public void removeProductFromCart(int cartId, int productId) {
        String sql = "DELETE FROM CART_DETAILS WHERE cartId = ? AND productId = ?";
        jdbcTemplate.update(sql, cartId, productId);
    }

    @Override
    public List<CartDetails> getProductsInCart(int cartId) {
        String sql = "SELECT * FROM CART_DETAILS WHERE cartId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToCartDetails(rs), cartId);
    }

}