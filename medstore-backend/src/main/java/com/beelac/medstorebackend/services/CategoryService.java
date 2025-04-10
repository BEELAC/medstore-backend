package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.Category;

import java.util.List;

public interface CategoryService {
	List<Category> getAllCategories();

	Category getCategoryById(int id);

	void addCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(int id);
}
