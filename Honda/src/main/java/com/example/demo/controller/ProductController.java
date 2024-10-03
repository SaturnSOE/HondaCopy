package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping("add")
	public ResponseEntity<String> add(@RequestBody Product p) {
		productServiceImpl.addProduct(p);
		return ResponseEntity.status(HttpStatus.CREATED).body("建檔成功");
	}
	
	@GetMapping("queryAll")
	public ResponseEntity<List<Product>> query() {
		List<Product> products = productServiceImpl.queryAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("queryByName/{name}")
	public ResponseEntity<List<Product>> queryByName(@PathVariable String name) {
		List<Product> products = productServiceImpl.queryByName(name);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("queryByModel/{model}")
	public ResponseEntity<List<Product>> queryByModel(@PathVariable String model) {
		List<Product> products = productServiceImpl.queryByModel(model);
		return ResponseEntity.ok(products);
	}
	
	@PutMapping("updateById/{id}")
	public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody Product product) {
		product.setId(id); 
		productServiceImpl.updateProduct(product);
		return ResponseEntity.ok("修改成功");
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		productServiceImpl.deleteById(id);
		return ResponseEntity.ok("刪除成功");
	}

	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
