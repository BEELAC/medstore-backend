package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.OrderDetailsDao;
import com.beelac.medstorebackend.model.OrderDetails;
import com.beelac.medstorebackend.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	private final OrderDetailsDao orderDetailsDao;

	@Autowired
	public OrderDetailsServiceImpl(OrderDetailsDao orderDetailDao) {
		this.orderDetailsDao = orderDetailDao;
	}

	@Override
	public OrderDetails getOrderDetailsById(int id) {
		return orderDetailsDao.getOrderDetailsById(id);
	}

	@Override
	public void addOrderDetails(OrderDetails orderDetails) {
		orderDetailsDao.addOrderDetails(orderDetails);
	}

	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
		return orderDetailsDao.getOrderDetailsByOrderId(orderId);
	}
}
