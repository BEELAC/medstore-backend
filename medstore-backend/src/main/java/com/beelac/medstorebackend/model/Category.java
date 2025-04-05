package com.beelac.medstorebackend.model;

public class Category {

	private int id;
	private String name;
	
	/* Constructor */
	public Category() {
		
	}
	
	/* Constructor w/ Attributes */
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* toString */
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
