package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.ProductDao;
import com.beelac.medstorebackend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Custom method to map result set to Product
    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("categoryId"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setDescription(rs.getString("description"));
        product.setCreatedOn(rs.getTimestamp("created_on"));
        return product;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM PRODUCT WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToProduct, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        String sql = "SELECT * FROM PRODUCT WHERE categoryId = ?";
        return jdbcTemplate.query(sql, this::mapRowToProduct, categoryId);
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO PRODUCT (categoryId, name, price, description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getCategoryId(), product.getName(), product.getPrice(), product.getDescription());
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE PRODUCT SET categoryId = ?, name = ?, price = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getCategoryId(), product.getName(), product.getPrice(), product.getDescription(), product.getId());
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM PRODUCT WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}