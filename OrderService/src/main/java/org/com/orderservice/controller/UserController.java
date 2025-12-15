package org.com.orderservice.controller;

import org.com.orderservice.dto.UserDto;
import org.com.orderservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/create")
	ResponseEntity<?> createdUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createdUser(userDto);
		if (createdUserDto == null)
			return new ResponseEntity<>("Something went wrong..", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/updateUser/{userId}")
	ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
		UserDto updatedUserDto = this.userService.updateUser(userId, userDto);
		if (updatedUserDto == null)
			return new ResponseEntity<>("Something went wrong..", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUserDto);
	}

}
