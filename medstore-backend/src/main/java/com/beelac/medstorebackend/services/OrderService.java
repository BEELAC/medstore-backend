package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.model.OrderRequest;

import java.util.List;

public interface OrderService {
	Order getOrderById(int id);

	void updateOrderStatus(int orderId, String status);

	List<Order> getOrdersByUserId(int userId);
	
	List<Order> getAllOrders();
	
	int createOrder(OrderRequest request);
}
