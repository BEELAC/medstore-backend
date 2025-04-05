package com.beelac.medstorebackend.model;

public class CartDetails {

	private int id;
	private int cartId;
	private int productId;
	private int quantity;
	
	/* Constructor */
	public CartDetails() {

	}

	/* Constructor w/ Attributes */
	public CartDetails(int id, int cartId, int productId, int quantity) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	/* toString */
	@Override
	public String toString() {
		return "CartDetails [id=" + id + ", cartId=" + cartId + ", productId=" + productId + ", quantity=" + quantity
				+ "]";
	}
	
}
