package org.com.orderservice.services;

import org.com.orderservice.dto.UserDto;

public interface UserService {
	UserDto createdUser(UserDto studentDto);
	UserDto updateUser(Long studentId, UserDto studentDto);
	void deleteUser(Long studentId);
}
