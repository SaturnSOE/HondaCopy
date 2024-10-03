package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Product;

@Mapper
public interface ProductMapper {
	
	@Insert("INSERT INTO product (name, model, description, price, image_url)"
			+ "VALUES (#{name}, #{model}, #{description}, #{price}, #{image_url})")
	public void insertProduct(Product product);
	
	@Select("SELECT * FROM product ORDER BY id DESC")
	public List<Product> selectAll();
	
	@Select("SELECT * FROM product WHERE id = #{id}")
	public List<Product> selectAllById(int id);
	
	@Select("SELECT * FROM product WHERE name = #{name}")
	public List<Product> selectAllByName(String name);
	
	@Select("SELECT * FROM product WHERE model = #{model}")
	public List<Product> selectAllByModel(String model);
	
	@Update("UPDATE product "
			+ "SET name = #{name}, model = #{model}, description = #{description}, "
				+ "price = #{price}, image_url = #{image_url} "
			+ "WHERE id = #{id}")
	public void updateProduct(Product product);
	
	@Delete("DELETE FROM product WHERE id = #{id}")
	public void deleteById(int id);
}
