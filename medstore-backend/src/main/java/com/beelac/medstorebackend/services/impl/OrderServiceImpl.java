package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.OrderDao;
import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderDao orderDao;

	@Autowired
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
	@Override
	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	@Override
	public void createOrder(Order order) {
		orderDao.createOrder(order);
	}

	@Override
	public void updateOrderStatus(int orderId, String status) {
		orderDao.updateOrderStatus(orderId, status);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}
}
