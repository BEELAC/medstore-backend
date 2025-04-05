package com.beelac.medstorebackend.dao;

import com.beelac.medstorebackend.model.OrderDetails;
import java.util.List;

public interface OrderDetailsDao {
	void addOrderDetails(OrderDetails orderDetails);

	List<OrderDetails> getOrderDetailsByOrderId(int orderId);

	OrderDetails getOrderDetailById(int orderDetailId);
}