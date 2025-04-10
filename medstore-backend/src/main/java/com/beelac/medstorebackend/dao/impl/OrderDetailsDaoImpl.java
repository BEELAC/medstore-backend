package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.OrderDetailsDao;
import com.beelac.medstorebackend.model.OrderDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    // Custom method to map result set to OrderDetails
    private OrderDetails mapRowToOrderDetails(ResultSet rs) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(rs.getInt("id"));
        orderDetails.setOrderId(rs.getInt("orderId"));
        orderDetails.setProductId(rs.getInt("productId"));
        orderDetails.setQuantity(rs.getInt("quantity"));
        orderDetails.setPrice(rs.getBigDecimal("price"));
        orderDetails.setTotal(rs.getBigDecimal("total"));
        return orderDetails;
    }

    @Override
    public void addOrderDetails(OrderDetails orderDetails) {
        String sql = "INSERT INTO ORDER_DETAILS (orderId, productId, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderDetails.getOrderId(), orderDetails.getProductId(), orderDetails.getQuantity(),
                orderDetails.getPrice(), orderDetails.getTotal());
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        String sql = "SELECT * FROM ORDER_DETAILS WHERE orderId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToOrderDetails(rs), orderId);
    }

    @Override
    public OrderDetails getOrderDetailsById(int orderDetailId) {
        String sql = "SELECT * FROM ORDER_DETAILS WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToOrderDetails(rs), orderDetailId);
    }

}