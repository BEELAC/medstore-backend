package com.beelac.medstorebackend.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {
    private int userId;
    private String paymentMethod;
    private String paymentStatus;
    private BigDecimal amount;
    private String orderStatus;
    private List<OrderItem> items;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequest [userId=" + userId + ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus + ", amount=" + amount +
                ", orderStatus=" + orderStatus + ", items=" + items + "]";
    }
}
