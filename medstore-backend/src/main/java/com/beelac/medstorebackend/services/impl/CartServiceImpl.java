package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.CartDao;
import com.beelac.medstorebackend.model.Cart;
import com.beelac.medstorebackend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	private final CartDao cartDao;

	@Autowired
	public CartServiceImpl(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	@Override
	public Cart getCartByUserId(int userId) {
		return cartDao.getCartByUserId(userId);
	}

	@Override
	public void deleteCart(int id) {
		cartDao.deleteCart(id);
	}
	
	@Override
	public void saveCart(Cart cart) {
		cartDao.saveCart(cart);
	}
}
