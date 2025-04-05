package com.beelac.medstorebackend.model;

public class Cart {

	private int id;
	private int userId;
	
	/* Constructor */
	public Cart() {

	}

	/* Constructor w/ Attributes */
	public Cart(int id, int userId) {
		super();
		this.id = id;
		this.userId = userId;
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

	/* toString */
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + "]";
	}
	
}
