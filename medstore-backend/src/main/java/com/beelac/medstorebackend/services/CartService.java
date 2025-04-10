package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.Cart;

public interface CartService {
	Cart getCartByUserId(int userId);

	void saveCart(Cart cart);

	void deleteCart(int id);
}
