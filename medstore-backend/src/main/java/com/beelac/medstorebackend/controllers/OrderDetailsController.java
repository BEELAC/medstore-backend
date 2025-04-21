package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.OrderDetails;
import com.beelac.medstorebackend.services.OrderDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable int id) {
        OrderDetails detail = orderDetailsService.getOrderDetailsById(id);
        return detail != null ? ResponseEntity.ok(detail) : ResponseEntity.notFound().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> getOrderDetailsByOrderId(@PathVariable int orderId) {
        return ResponseEntity.ok(orderDetailsService.getOrderDetailsByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<Void> addOrderDetails(@RequestBody OrderDetails orderDetails) {
        orderDetailsService.addOrderDetails(orderDetails);
        return ResponseEntity.ok().build();
    }
}
