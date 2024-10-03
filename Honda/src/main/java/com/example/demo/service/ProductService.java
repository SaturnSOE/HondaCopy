package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
	
	//create
	void addProduct(Product product);
	
	//read
	List<Product> queryAll();
	Product queryById(int id);
	List<Product> queryByName(String name);
	List<Product> queryByModel(String model);
	
	//update
	void updateProduct(Product product);
	
	//delete
	void deleteById(int id);
}
