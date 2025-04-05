package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.CategoryDao;
import com.beelac.medstorebackend.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Custom method to map result set to Category
    private Category mapRowToCategory(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        return category;
    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM CATEGORY WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToCategory, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM CATEGORY";
        return jdbcTemplate.query(sql, this::mapRowToCategory);
    }

    @Override
    public void addCategory(Category category) {
        String sql = "INSERT INTO CATEGORY (name) VALUES (?)";
        jdbcTemplate.update(sql, category.getName());
    }

    @Override
    public void updateCategory(Category category) {
        String sql = "UPDATE CATEGORY SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getId());
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE FROM CATEGORY WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}