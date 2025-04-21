package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.dao.OrderDao;
import com.beelac.medstorebackend.dao.OrderDetailsDao;
import com.beelac.medstorebackend.dao.ProductDao;
import com.beelac.medstorebackend.model.Cart;
import com.beelac.medstorebackend.model.CartDetails;
import com.beelac.medstorebackend.model.Order;
import com.beelac.medstorebackend.model.OrderDetails;
import com.beelac.medstorebackend.model.Product;
import com.beelac.medstorebackend.services.CartDetailsService;
import com.beelac.medstorebackend.services.CartService;
import com.beelac.medstorebackend.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderDetailsDao orderDetailsDao;
    private final CartService cartService;
    private final CartDetailsService cartDetailsService;
    private final ProductDao productDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            OrderDetailsDao orderDetailsDao,
                            CartService cartService,
                            CartDetailsService cartDetailsService,
                            ProductDao productDao) {
        this.orderDao = orderDao;
        this.orderDetailsDao = orderDetailsDao;
        this.cartService = cartService;
        this.cartDetailsService = cartDetailsService;
        this.productDao = productDao;
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

    // ✅ New checkout logic
    @Override
    public void placeOrderFromCart(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("No cart found for user.");
        }

        int cartId = cart.getId();
        List<CartDetails> items = cartDetailsService.getProductsInCart(cartId);

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        Order order = new Order();
        order.setUserId(userId);

        int orderId = orderDao.createOrder(order);

        for (CartDetails item : items) {
            Product product = productDao.getProductById(item.getProductId());
            if (product == null) continue;

            OrderDetails details = new OrderDetails();
            details.setOrderId(orderId);
            details.setProductId(item.getProductId());
            details.setQuantity(item.getQuantity());
            details.setPrice(product.getPrice());
            details.setTotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            orderDetailsDao.addOrderDetails(details);
        }

        // clear cart after order is placed
        for (CartDetails item : items) {
            cartDetailsService.removeProductFromCart(cartId, item.getProductId());
        }
    }
}
