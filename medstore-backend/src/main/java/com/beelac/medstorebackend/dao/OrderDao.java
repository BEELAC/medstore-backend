package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.Order;
import java.util.List;

public interface OrderDao {
	void createOrder(Order order);

	List<Order> getOrdersByUserId(int userId);

	Order getOrderById(int orderId);

	void updateOrderStatus(int orderId, String status);
	
	List<Order> getAllOrders();
}