package org.com.orderservice.repositories;

import java.util.List;

import org.com.orderservice.entities.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	List<OrderHistory> findByOrderId(Long id);
}
