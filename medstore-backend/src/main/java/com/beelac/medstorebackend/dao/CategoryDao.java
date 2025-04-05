package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.Category;
import java.util.List;

public interface CategoryDao {
	Category getCategoryById(int id);

	List<Category> getAllCategories();

	void addCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(int id);
}
