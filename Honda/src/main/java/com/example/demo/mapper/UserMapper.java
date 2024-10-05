package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO user (name, username, password, phone, email, address, gender, driving_license)"
			+ "VALUES (#{name}, #{username}, #{password}, #{phone}, #{email}, #{address}, #{gender}, #{driving_license})")
	public void insert(User user);
	
	
	
	@Select("SELECT * FROM user ORDER BY id DESC")
	public List<User> selectAll();
	
	@Select("SELECT * FROM user WHERE user_id = #{user_id}")
	public Optional<User> selectAllById(int user_id);
	
	@Select("SELECT * FROM user WHERE name = #{name}")
	public List<User> selectAllByName(String name);
	
	@Select("SELECT COUNT(*) FROM user WHERE username = #{username}")
	public int countByUserName(String username);
	
	@Select("SELECT COUNT(*) FROM user WHERE password = #{password}")
	public int countByPassword(String password);
	
	@Select("SELECT COUNT(*) FROM user WHERE phone = #{phone}")
	public int countByPhone(String phone);
	
	@Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
	public int countByEmail(String email);
	
	
	
	@Update("UPDATE user "
			+ "SET name = #{name}, password = #{passowrd}, phone = #{phone}, email = #{email}, "
				+ "address = #{address}, gender = #{gender}, driving_license = #{driving_license} "
			+ "WHERE user_id = #{user_id}")
	public void updateUser(User user);
	
	
	
	@Delete("DELETE FROM user WHERE user_id = #{user_id}")
	public void deleteUserById(int user_id);
}
