package com.beelac.medstorebackend.model;

import java.sql.Timestamp;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String city;
	private String creditCard;
	private String phone;
	private Timestamp createdOn;
	private Timestamp modifiedOn;
	private boolean isAdmin;

	/* Constructor */
	public User() {

	}

	/* Constructor w/ Attributes */
	public User(int id, String username, String password, String name, String email, String address, String city,
			String creditCard, String phone, Timestamp createdOn, Timestamp modifiedOn, boolean isAdmin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.creditCard = creditCard;
		this.phone = phone;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isAdmin = isAdmin;
	}

	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/* toString */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", address=" + address + ", city=" + city + ", creditCard=" + creditCard + ", phone=" + phone
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", isAdmin=" + isAdmin + "]";
	}

}
