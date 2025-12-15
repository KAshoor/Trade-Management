package org.com.orderservice.repositories;

import java.util.List;
import java.util.Optional;

import org.com.orderservice.entities.User;
import org.com.orderservice.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserRole(UserRole userRole);

	Optional<User> findByEmail(String email);

	List<User> findAllByUserRole(UserRole userRole);
}
