package com.beelac.medstorebackend.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

	private int id;
	private int userId;
	private String paymentMethod;
	private String paymentStatus;
	private BigDecimal amount;
	private String orderStatus;
	
	/* Constructor */
	public Order() {
		
	}

	/* Constructor w/ Attributes */
	public Order(int id, int userId, String paymentMethod, String paymentStatus, BigDecimal amount,
			String orderStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.amount = amount;
		this.orderStatus = orderStatus;
	}

	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	/* toString */
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", paymentMethod=" + paymentMethod + ", paymentStatus="
				+ paymentStatus + ", amount=" + amount + ", orderStatus=" + orderStatus + "]";
	}
	
}
