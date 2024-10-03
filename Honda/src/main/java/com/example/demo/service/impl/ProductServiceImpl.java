package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public void addProduct(Product product) {
        validateProduct(product);
        productMapper.insertProduct(product);
    }
    
    @Override
    public List<Product> queryAll() {
        List<Product> products = productMapper.selectAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found.");
        }
        return products;
    }


    @Override
    public Product queryById(int id) {
        validateId(id);
        return productMapper.selectAllById(id)
                .stream()		  		
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }


    @Override
    public List<Product> queryByName(String name) {
        validateString(name, "Name");
        List<Product> products = productMapper.selectAllByName(name);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with name: " + name);
        }
        return products;
    }

    @Override
    public List<Product> queryByModel(String model) {
        validateString(model, "Model");
        List<Product> products = productMapper.selectAllByModel(model);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with model: " + model);
        }
        return products;
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        validateId(product.getId());
        validateProduct(product);
        if (productMapper.selectAllById(product.getId()).isEmpty()) {
            throw new ProductNotFoundException("Product with id " + product.getId() + " not found");
        }
        productMapper.updateProduct(product);
    }
    
    @Override
    @Transactional
    public void deleteById(int id) {
        validateId(id);
        if (productMapper.selectAllById(id).isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productMapper.deleteById(id);
    }
    
    
    
    
    private void validateProduct(Product product) {
        if (product == null || isProductDataInvalid(product)) {
            throw new IllegalArgumentException("Product data is invalid");
        }
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

	private boolean isProductDataInvalid(Product product) {
        String name = product.getName();
        String model = product.getModel();
        String description = product.getDescription();
        BigDecimal price = product.getPrice();
        String image = product.getImage_url();

        return (name == null || name.isEmpty()) ||
               (model == null || model.isEmpty()) ||
               (description == null || description.isEmpty()) ||
               (price == null) ||
               (image == null || image.isEmpty());
    }
	
}
