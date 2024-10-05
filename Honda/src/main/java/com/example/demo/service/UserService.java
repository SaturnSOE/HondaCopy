package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	
	//Create
	void addUser(User user);
	
	//Read
	List<User> selectAll();
	User selectById(int user_id);
	List<User> selectByName(String name);
	
	//Update
	void update(User user);
	
	//Delete
	void deleteById(int user_id);
}
