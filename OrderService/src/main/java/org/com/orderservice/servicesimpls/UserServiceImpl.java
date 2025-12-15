package org.com.orderservice.servicesimpls;

import java.util.Optional;

import org.com.orderservice.dto.UserDto;
import org.com.orderservice.entities.User;
import org.com.orderservice.enums.UserRole;
import org.com.orderservice.repositories.UserRepository;
import org.com.orderservice.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	

	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = this.userRepo.findByUserRole(UserRole.ADMIN);
		if (adminAccount == null) {
			User newAdminUser = new User();
			newAdminUser.setEmail("admin@test.com");
			newAdminUser.setName("admin");
			newAdminUser.setUserRole(UserRole.ADMIN);
			newAdminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
			this.userRepo.save(newAdminUser);
		}

	}


	@Override
	public UserDto createdUser(UserDto userDto) {
		Optional<User> optionUser = this.userRepo.findByEmail(userDto.getEmail());
		if (optionUser.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
			user.setUserRole(UserRole.TRADER);
			User createdUser = this.userRepo.save(user);
			UserDto createdUserDto = new UserDto();
			createdUserDto.setId(createdUser.getId());
			createdUserDto.setEmail(createdUser.getEmail());
			return createdUserDto;
		}
		return null;
	}


	@Override
	public UserDto updateUser(Long studentId, UserDto studentDto) {
		Optional<User> optionalUser = this.userRepo.findById(studentId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(studentDto.getName());
			user.setEmail(studentDto.getEmail());
			User upUser = this.userRepo.save(user);
			UserDto updatedUserDto = new UserDto();
			updatedUserDto.setId(upUser.getId());
			return updatedUserDto;
		}
		return null;
	}


	@Override
	public void deleteUser(Long studentId) {
		this.userRepo.deleteById(studentId);
		
	}


	

}
