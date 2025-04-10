package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.CategoryDao;
import com.beelac.medstorebackend.model.Category;
import com.beelac.medstorebackend.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(int id) {
		categoryDao.deleteCategory(id);
	}
}
