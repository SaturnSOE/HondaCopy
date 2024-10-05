package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
	
	private int employee_id;
	private String name;
	private String username;
	private String password;
	private String phone_number;
	private String job_title;
	
	public Employee(String name, String username, String password, String phone_number, String job_title) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.job_title = job_title;
	}
	
	
}
