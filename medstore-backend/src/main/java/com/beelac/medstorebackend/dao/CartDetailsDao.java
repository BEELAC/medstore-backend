package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.CartDetails;
import java.util.List;

public interface CartDetailsDao {
	void addProductToCart(CartDetails cartDetails);

	void updateProductQuantity(int cartId, int productId, int quantity);

	void removeProductFromCart(int cartId, int productId);

	List<CartDetails> getProductsInCart(int cartId);
}
