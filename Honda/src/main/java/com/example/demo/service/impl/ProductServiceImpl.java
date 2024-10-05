package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.product.InvalidProductDataException;
import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        validateProduct(product);
        validateUniqueName(product);
        
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
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }


    @Override
    public List<Product> queryByName(String name) {
        validateNonNullAndNonEmptyString(name, "Name");
        List<Product> products = productMapper.selectAllByName(name);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with name: " + name);
        }
        
        return products;
    }

    @Override
    public List<Product> queryByModel(String model) {
        validateNonNullAndNonEmptyString(model, "Model");
        List<Product> products = productMapper.selectAllByModel(model);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with model: " + model);
        }
        
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        validateProduct(product);
        validateId(product.getId());
        ensureProductExists(product.getId());
        validateUniqueName(product);
        
        productMapper.updateProduct(product);
    }
    
    @Override
    public void deleteById(int id) {
        validateId(id);
        ensureProductExists(id);
        
        productMapper.deleteById(id);
    }
    
    
    
    
    
    
    private void validateProduct(Product product) {
        if (product == null || isProductDataInvalid(product)) {
            throw new InvalidProductDataException("Product data is invalid");
        }
    }

    private static final int MIN_VALID_ID = 1;
    private void validateId(int id) {
        if (id < MIN_VALID_ID) {
            throw new InvalidProductDataException("ID must be a positive number");
        }
    }
    
    private void ensureProductExists(int id) {
        if (productMapper.selectAllById(id).isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }


    private void validateNonNullAndNonEmptyString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new InvalidProductDataException(fieldName + " cannot be null or empty");
        }
    }

    private void validateUniqueName(Product product) {
    	if (productMapper.countByName(product.getName()) > 0) {
    		throw new InvalidProductDataException(product.getName() + " already exists");
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
