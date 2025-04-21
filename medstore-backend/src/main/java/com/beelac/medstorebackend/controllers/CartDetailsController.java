package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.CartDetails;
import com.beelac.medstorebackend.services.CartDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-details")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartDetailsController {

    @Autowired
    private CartDetailsService cartDetailsService;

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartDetails>> getProductsInCart(@PathVariable int cartId) {
        return ResponseEntity.ok(cartDetailsService.getProductsInCart(cartId));
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestBody CartDetails cartDetails) {
        cartDetailsService.addProductToCart(cartDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateProductQuantity(@RequestParam int cartId,
                                                      @RequestParam int productId,
                                                      @RequestParam int quantity) {
        cartDetailsService.updateProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeProductFromCart(@RequestParam int cartId,
                                                      @RequestParam int productId) {
        cartDetailsService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }
}
