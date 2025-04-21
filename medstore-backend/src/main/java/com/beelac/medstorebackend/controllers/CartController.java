package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.Cart;
import com.beelac.medstorebackend.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }
}
