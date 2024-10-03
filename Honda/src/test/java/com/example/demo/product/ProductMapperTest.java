package com.example.demo.product;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;

@SpringBootTest
public class ProductMapperTest {
	
	@Autowired
	ProductMapper productMapper;
	
//	@Test
	public String InsertTest()
	{
		double price = 46650.90;
		BigDecimal value = new BigDecimal(price);
		
		Product porduct = new Product("Neon Blaze", "Model NB68R", "A futuristic motorcycle with an eye-catching design and high performance."
								, value, "https://example.com/images/neon_blaze.jpg");
		
		productMapper.insertProduct(porduct);
		
		return "建檔成功";
	}
	
	@Test
	public List<Product> selectByNameTest()
	{
		return productMapper.selectAllByName("Blaze X");
	}
}
