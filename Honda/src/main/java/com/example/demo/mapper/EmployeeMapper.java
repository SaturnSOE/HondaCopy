package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Employee;

@Mapper
public interface EmployeeMapper {
	
	@Insert("INSERT INTO employee (name, username, password, phone_number, job_title) "
			+ "VALUES (#{name}, #{username}, #{password}, #{phone_number}, #{job_title})")
	public void insert(Employee employee);
	
	@Select("SELECT * FROM employee WHERE employee_id = #{employee_id}")
	public List<Employee> selectById(int employee_id);
	
	@Select("SELECT * FROM employee WHERE name = #{name}")
	public List<Employee> selectByName(String name);
	
	@Select("SELECT * FROM employee WHERE job_title = #{job_title}")
	public List<Employee> selectByTitle(String job_title);
	
	@Select("SELECT COUNT(*) FROM employee WHERE username = #{username}")
	public int countByUsername(String username);
	
	@Select("SELECT COUNT(*) FROM employee WHERE password = #{password}")
	public int countByPassword(String password);
	
	@Select("SELECT COUNT(*) FROM employee WHERE phone_number = #{phone_number}")
	public int countByPhone_number(String phone_number);
	
	@Update("UPDATE employee "
			+ "SET name = #{name}, password = #{password}, phone_number = #{phone_number}, job_title = #{job_title} "
			+ "WHERE employee_id = #{employee_id}")
	public void update(Employee employee);
	
	@Delete("DELETE FROM employee WHERE employee_id = #{employee_id}")
	public void delete(int employee_id);
}
