package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.Cart;

public interface CartDao {
	Cart getCartByUserId(int userId);

	void saveCart(Cart cart);

	void deleteCart(int cartId);
}