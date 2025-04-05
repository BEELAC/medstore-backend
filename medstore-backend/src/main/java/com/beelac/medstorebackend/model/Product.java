package com.beelac.medstorebackend.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {

	private int id;
	private int categoryId;
	private String name;
	private BigDecimal price;
	private String description;
	private Timestamp createdOn;
	
	/* Constructor */
	public Product() {
		
	}

	/* Constructor w/ Attributes */
	public Product(int id, int categoryId, String name, BigDecimal price, String description, Timestamp createdOn) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.createdOn = createdOn;
	}

	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/* toString */
	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", createdOn=" + createdOn + "]";
	}
	
}
