package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.services.OrderService;
import com.beelac.medstorebackend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order, Authentication authentication) {
        System.out.println("Authenticated user: " + authentication.getName());
        System.out.println("Creating order: " + order);
        orderService.createOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok().build();
    }

    // new checkout endpoint
    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutFromCart(Authentication auth) {
        String email = auth.getName();
        int userId = userService.getUserByEmail(email).getId();

        orderService.placeOrderFromCart(userId);
        return ResponseEntity.ok("Order placed successfully.");
    }
}
