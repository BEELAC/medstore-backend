package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.CartDetailsDao;
import com.beelac.medstorebackend.model.CartDetails;
import com.beelac.medstorebackend.services.CartDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {

	private final CartDetailsDao cartDetailsDao;
	
    @Autowired
    public CartDetailsServiceImpl(CartDetailsDao cartDetailsDao) {
        this.cartDetailsDao = cartDetailsDao;
    }

	@Override
	public void addProductToCart(CartDetails cartDetails) {
		cartDetailsDao.addProductToCart(cartDetails);
	}

	@Override
	public void updateProductQuantity(int cartId, int productId, int quantity) {
		cartDetailsDao.updateProductQuantity(cartId, productId, quantity);
	}

	@Override
	public void removeProductFromCart(int cartId, int productId) {
		cartDetailsDao.removeProductFromCart(cartId, productId);
	}

	@Override
	public List<CartDetails> getProductsInCart(int cartId) {
		return cartDetailsDao.getProductsInCart(cartId);
	}

}
