package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.OrderDao;
import com.beelac.medstorebackend.dao.OrderDetailsDao;
import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.model.OrderDetails;
import com.beelac.medstorebackend.model.OrderItem;
import com.beelac.medstorebackend.model.OrderRequest;
import com.beelac.medstorebackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderDetailsDao orderDetailsDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderDetailsDao orderDetailsDao) {
        this.orderDao = orderDao;
        this.orderDetailsDao = orderDetailsDao;
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
    public void updateOrderStatus(int orderId, String status) {
        orderDao.updateOrderStatus(orderId, status);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public int createOrder(OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setPaymentStatus(request.getPaymentStatus());
        order.setAmount(request.getAmount());
        order.setOrderStatus(request.getOrderStatus());

        // insert into orders table
        int orderId = orderDao.createOrder(order);

        // insert each item into order_details
        for (OrderItem item : request.getItems()) {
            OrderDetails details = new OrderDetails();
            details.setOrderId(orderId);
            details.setProductId(item.getProductId());
            details.setQuantity(item.getQuantity());
            details.setPrice(item.getPrice());
            details.setTotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            orderDetailsDao.addOrderDetails(details);
        }

        return orderId;
    }
}
