package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.Order;

import java.util.List;

public interface OrderService {
	Order getOrderById(int id);

	void createOrder(Order order);

	void updateOrderStatus(int orderId, String status);

	List<Order> getOrdersByUserId(int userId);
}
