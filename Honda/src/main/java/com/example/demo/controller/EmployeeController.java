package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.employee.EmployeeNotFoundException;
import com.example.demo.exception.employee.InvalidEmployeeDataException;
import com.example.demo.model.Employee;
import com.example.demo.service.impl.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping("add")
	@Transactional
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee)
	{
		employeeServiceImpl.add(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body("建檔成功");
	}
	
	@GetMapping("queryById/{id}")
	public ResponseEntity<Employee> queryById(@PathVariable int employee_id) {
		Employee employee = employeeServiceImpl.queryById(employee_id);
		return ResponseEntity.ok(employee);
	}
	
	@GetMapping("queryByName/{name}")
	public ResponseEntity<List<Employee>> queryByName(@PathVariable String name) {
		List<Employee> employees = employeeServiceImpl.queryByName(name);
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("queryByTitle/{title}")
	public ResponseEntity<List<Employee>> queryByTitle(@PathVariable String job_title) {
		List<Employee> employees = employeeServiceImpl.queryByTitle(job_title);
		return ResponseEntity.ok(employees);
	}
	
	@PutMapping("update/{id}")
	@Transactional
	public ResponseEntity<String> update(@PathVariable int employee_id, @RequestBody Employee employee) {
		employee.setEmployee_id(employee_id);
		employeeServiceImpl.update(employee);
		return ResponseEntity.ok("Employee_ID: " + employee.getEmployee_id() + " 修改成功");
	}
	
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<String> delete(@PathVariable int employee_id) {
		employeeServiceImpl.delete(employee_id);
		return ResponseEntity.ok("刪除成功");
	}
	
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(InvalidEmployeeDataException.class)
	public ResponseEntity<String> handleInvalidEmployeeData(InvalidEmployeeDataException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	
	
//	@GetMapping("Login")
	public ModelAndView gotoLogin()
	{
		ModelAndView mav = new ModelAndView("Login");
		return mav;
	}
}
