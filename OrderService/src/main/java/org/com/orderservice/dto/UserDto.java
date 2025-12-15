package org.com.orderservice.dto;

import org.com.orderservice.enums.UserRole;

public class UserDto {

	private Long id;

	private String name;
	private String email;
	private UserRole userRole;
	private String password;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, String name, String email, UserRole userRole, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userRole = userRole;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
