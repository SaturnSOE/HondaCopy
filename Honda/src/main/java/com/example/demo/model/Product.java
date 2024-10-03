package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Product {
	
	private int id;
	private String name;
	private String model;
	private String description;
	private BigDecimal price;
	private String image_url;
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
	
    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Product(String name, String model, String description, BigDecimal price, String image_url) {
		super();
		this.name = name;
		this.model = model;
		this.description = description;
		this.price = price;
		this.image_url = image_url;
	}



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



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public String getImage_url() {
		return image_url;
	}



	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
