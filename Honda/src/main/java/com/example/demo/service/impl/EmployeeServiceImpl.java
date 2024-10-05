package com.example.demo.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.employee.EmployeeNotFoundException;
import com.example.demo.exception.employee.InvalidEmployeeDataException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public void add(Employee employee) {
		validateEmployee(employee);
		validateUniqueFields(employee);
		
		employeeMapper.insert(employee);
	}

	@Override
	public Employee queryById(int employee_id) {
		validateEmployeeId(employee_id);
		return employeeMapper.selectById(employee_id)
				.stream()
				.findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employee_id + " not found"));
	}

	@Override
	public List<Employee> queryByName(String name) {
		validateNonNullAndNonEmptyString(name, "Name");
		List<Employee> employees = employeeMapper.selectByName(name);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees found with name: " + name);
		}
		
		return employees;
	}

	@Override
	public List<Employee> queryByTitle(String job_title) {
		validateNonNullAndNonEmptyString(job_title, "Job_Title");
		List<Employee> employees = employeeMapper.selectByTitle(job_title);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees found with job_title: " + job_title);
		}
		
		return employees;
	}

	@Override
	public void update(Employee employee) {
		validateEmployeeId(employee.getEmployee_id());
		ensureEmployeeExists(employee.getEmployee_id());
		validateEmployee(employee);
		validateUniqueFields(employee);
		
		employeeMapper.update(employee);
	}

	@Override
	public void delete(int employee_id) {
		validateEmployeeId(employee_id);
		ensureEmployeeExists(employee_id);
		
		employeeMapper.delete(employee_id);
	}
	
	
	
	
	
	private void validateEmployee(Employee employee) {
		if (employee == null || isEmployeeDataInvalid(employee)) {
            throw new InvalidEmployeeDataException("Employee data is invalid");
        }
	}
	
	private static final int MIN_VALID_ID = 1;
	private void validateEmployeeId(int employee_id) {
		if (employee_id < MIN_VALID_ID) {
			throw new InvalidEmployeeDataException("ID must be a positive number");
		}
	}
	
	private void ensureEmployeeExists(int employee_id) {
	    if (employeeMapper.selectById(employee_id).isEmpty()) {
	        throw new EmployeeNotFoundException("EmployeeID: " + employee_id + " not found");
	    }
	}
	
	private void validateUniqueField(String fieldValue, String fieldName, Function<String, Integer> countFunction) {
	    if (countFunction.apply(fieldValue) > 0) {
	        throw new InvalidEmployeeDataException(fieldName + " already exists");
	    }
	}

	private void validateUniqueFields(Employee employee) {
	    validateUniqueField(employee.getUsername(), "Username", employeeMapper::countByUsername);
	    validateUniqueField(employee.getPassword(), "Password", employeeMapper::countByPassword);
	    validateUniqueField(employee.getPhone_number(), "Phone number", employeeMapper::countByPhone_number);
	}

	
	private void validateNonNullAndNonEmptyString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new InvalidEmployeeDataException(fieldName + " cannot be null or empty");
        }
    }
	
	private boolean isEmployeeDataInvalid(Employee employee) {
		String name = employee.getName();
		String username = employee.getUsername();
		String password = employee.getPassword();
		String phone_number= employee.getPhone_number();
		String job_title = employee.getJob_title();
		
		return	(name == null || name.isEmpty()) ||
				(username == null || username.isEmpty()) ||
				(password == null || password.isEmpty()) ||
				(phone_number == null || phone_number.isEmpty()) ||
				(job_title == null || job_title.isEmpty());
	}
}
