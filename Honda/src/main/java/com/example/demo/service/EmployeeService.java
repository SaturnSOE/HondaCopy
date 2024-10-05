package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	//Create
	void add(Employee employee);
	
	//Read
	Employee queryById(int employee_id);
	List<Employee> queryByName(String name);
	List<Employee> queryByTitle(String job_title);
	
	//Update
	void update(Employee employee);
	
	//Delete
	void delete(int employee_id);
}
