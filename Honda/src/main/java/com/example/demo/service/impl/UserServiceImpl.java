package com.example.demo.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.user.InvalidUserDataException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public void addUser(User user) {
		validateUser(user);
		validateUniqueFields(user);
		
		userMapper.insert(user);
	}

	@Override
	public List<User> selectAll() {
		List<User> users = userMapper.selectAll();
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found.");
		}
		
		return users;
	}

	@Override
	public User selectById(int user_id) {
		validateId(user_id);
		return userMapper.selectAllById(user_id)
				.orElseThrow(() -> new UserNotFoundException("User with id " + user_id + " not found"));
	}

	@Override
	public List<User> selectByName(String name) {
		validateNonNullAndNonEmptyString(name, "Name");
		List<User> users = userMapper.selectAllByName(name);
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found.");
		}
		
		return users;
	}

	@Override
	public void update(User user) {
		validateId(user.getUser_id());
		ensureUserExists(user.getUser_id());
		validateUser(user);
		validateUniqueFields(user);
		
		userMapper.updateUser(user);
	}

	@Override
	public void deleteById(int user_id) {
		validateId(user_id);
		ensureUserExists(user_id);
		
		userMapper.deleteUserById(user_id);
	}
	
	
	
	private void validateUser(User user) {
        if (user == null || isUserDataInvalid(user)) {
            throw new InvalidUserDataException("User data is invalid");
        }
    }

    private static final int MIN_VALID_ID = 1;
    private void validateId(int user_id) {
        if (user_id < MIN_VALID_ID) {
            throw new InvalidUserDataException("ID must be a positive number");
        }
    }
    
    private void ensureUserExists(int user_id) {
        if (userMapper.selectAllById(user_id).isEmpty()){
            throw new UserNotFoundException("User with id " + user_id + " not found");
        }
    }

    private void validateUniqueField(String fieldValue, String fieldName, Function<String, Integer> countFunction) {
	    if (countFunction.apply(fieldValue) > 0) {
	        throw new InvalidUserDataException(fieldName + " already exists");
	    }
	}

	private void validateUniqueFields(User user) {
	    validateUniqueField(user.getUsername(), "UserName", userMapper::countByUserName);
	    validateUniqueField(user.getPassword(), "Password", userMapper::countByPassword);
	    validateUniqueField(user.getPhone(), "Phone_Number", userMapper::countByPhone);
	    validateUniqueField(user.getEmail(), "Email", userMapper::countByEmail);
	}
    
    private void validateNonNullAndNonEmptyString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new InvalidUserDataException(fieldName + " cannot be null or empty");
        }
    }
    
    
	private boolean isUserDataInvalid(User user) {
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        String gender = user.getGender();
        String driving_license = user.getDriving_license();

        return (name == null || name.isEmpty()) ||
               (username == null || username.isEmpty()) ||
               (password == null || password.isEmpty()) ||
               (phone == null || phone.isEmpty()) ||
               (email == null || email.isEmpty()) ||
               (gender == null || gender.isEmpty()) ||
               (driving_license == null || driving_license.isEmpty());
    }
}
