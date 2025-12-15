package org.com.orderservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

import org.com.orderservice.entities.User;
import org.com.orderservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailedServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = this.userRepository.findByEmail(email);
		if (userOptional.isEmpty())
			throw new UsernameNotFoundException("User Not Found", null);
		return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),
				userOptional.get().getPassword(), new ArrayList<>());
	}
}
