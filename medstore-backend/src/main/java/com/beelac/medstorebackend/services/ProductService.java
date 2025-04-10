package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.Product;

import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(int id);

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id);

	List<Product> getProductsByCategory(int categoryId);
}
