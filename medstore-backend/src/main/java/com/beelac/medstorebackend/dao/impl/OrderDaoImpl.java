package com.beelac.medstorebackend.dao.impl;

import com.beelac.medstorebackend.dao.OrderDao;
import com.beelac.medstorebackend.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;

    // Custom method to map result set to Orders
    private Order mapRowToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("userId"));
        order.setPaymentMethod(rs.getString("payment_method"));
        order.setPaymentStatus(rs.getString("payment_status"));
        order.setAmount(rs.getBigDecimal("amount"));
        order.setOrderStatus(rs.getString("order_status"));
        return order;
    }

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders (userId, payment_status, payment_method, amount, order_status) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getOrderStatus());
            ps.setString(3, order.getPaymentMethod());
            ps.setBigDecimal(4, order.getAmount());
            ps.setString(5, order.getPaymentStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM ORDERS WHERE userId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToOrder(rs), userId);
    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM ORDERS WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToOrder(rs), orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE ORDERS SET order_status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, orderId);
    }

	@Override
	public List<Order> getAllOrders() {
	    String sql = "SELECT * FROM ORDERS";
	    return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToOrder(rs));
	}
}
