package com.beelac.medstorebackend.services;

import java.util.List;

import com.beelac.medstorebackend.model.CartDetails;

public interface CartDetailsService {
	void addProductToCart(CartDetails cartDetails);

	void updateProductQuantity(int cartId, int productId, int quantity);

	void removeProductFromCart(int cartId, int productId);

	List<CartDetails> getProductsInCart(int cartId);
}
