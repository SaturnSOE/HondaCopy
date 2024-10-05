package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.user.InvalidUserDataException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("addUser")
	public ResponseEntity<String> add(@RequestBody User user)
	{
		userServiceImpl.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("建檔成功");
	}
	
	@GetMapping("queryAll")
	public ResponseEntity<List<User>> queryAll()
	{
		List<User> users = userServiceImpl.selectAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("queryById/{user_id}")
	public ResponseEntity<User> queryByUserId(@PathVariable int user_id)
	{
		User user = userServiceImpl.selectById(user_id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("queryByName/{name}")
	public ResponseEntity<List<User>> queryByName(@PathVariable String name)
	{
		List<User> users = userServiceImpl.selectByName(name);
		return ResponseEntity.ok(users);
	} 
	
	@PutMapping("updateUser/{user_id}")
	public ResponseEntity<String> update(@PathVariable int user_id, @RequestBody User user)
	{
		user.setUser_id(user_id);
		userServiceImpl.update(user);
		return ResponseEntity.ok("User_ID: " + user.getUser_id() + " 修改成功");
	}
	
	@DeleteMapping("deleteUser/{user_id}")
	public ResponseEntity<String> delete(@PathVariable int user_id)
	{
		userServiceImpl.deleteById(user_id);
		return ResponseEntity.ok("刪除成功");
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidUserDataException.class)
	public ResponseEntity<String> handleInvalidUserData(InvalidUserDataException ex)
	{
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
