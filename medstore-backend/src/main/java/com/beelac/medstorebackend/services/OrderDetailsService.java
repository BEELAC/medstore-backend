package com.beelac.medstorebackend.services;

import com.beelac.medstorebackend.model.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
	OrderDetails getOrderDetailsById(int id);

	void addOrderDetails(OrderDetails orderDetails);

	List<OrderDetails> getOrderDetailsByOrderId(int orderId);
}
