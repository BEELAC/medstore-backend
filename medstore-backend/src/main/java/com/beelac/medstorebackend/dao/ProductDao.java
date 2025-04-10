package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.Product;

import java.util.List;

public interface ProductDao {
	Product getProductById(int id);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(int categoryId);

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id);
}
