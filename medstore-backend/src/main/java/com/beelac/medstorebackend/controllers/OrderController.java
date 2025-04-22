package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.model.OrderRequest;
import com.beelac.medstorebackend.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
    	System.out.println("Incoming order payload: " + orderRequest);

    	if (orderRequest.getItems() == null) {
    	    System.out.println("orderRequest.getItems() is NULL");
    	} else {
    	    System.out.println("Items received: " + orderRequest.getItems());
    	}
    	
        try {
            int orderId = orderService.createOrder(orderRequest);
            return ResponseEntity.ok("Order placed successfully with ID: " + orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order");
        }
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
}
