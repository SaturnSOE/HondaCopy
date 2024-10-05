package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	private int user_id;
	private String name;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String gender;
	private String driving_license;
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	
    public User(String name, String username, String password, String phone, String email, String address,
			String gender, String driving_license) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.driving_license = driving_license;
	}
    
    
}
