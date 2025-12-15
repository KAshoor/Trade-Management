package org.com.orderservice.repositories;

import org.com.orderservice.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByStatus(String status, Pageable pageable);

	Page<Order> findByUserId(Long userId, Pageable pageable);

	Page<Order> findAll(Pageable pageable);

	Page<Order> findByStatusAndSymbol(String status, String symbol, Pageable pageable);

	Page<Order> findByUserIdAndStatusAndSymbol(Long userId, String status, String symbol, Pageable pageable);

	Page<Order> findByUserIdAndStatus(Long userId, String status, Pageable pageable);

}
