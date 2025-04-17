package com.beelac.medstorebackend.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDetails {

	private int id;
	private int orderId;
	private int productId;
	private int quantity;
	private BigDecimal price;
	private BigDecimal total; // Generated in DB
	private Timestamp createdOn;
	
	// Constructor
	public OrderDetails() {

	}
	
	// Constructor w/ Attributes
	public OrderDetails(int id, int orderId, int productId, int quantity, BigDecimal amount, BigDecimal total) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = amount;
		this.total = total;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	// toString
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity
				+ ", amount=" + price + ", total=" + total + "]";
	}
	
}
