package org.com.orderservice.services;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.com.orderservice.dto.OrderRequest;
import org.com.orderservice.dto.OrderResponse;
import org.com.orderservice.entities.Order;
import org.com.orderservice.entities.OrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest req, String authHeader) throws AccessDeniedException;

	public OrderResponse getOrder(Long orderId, String authHeader) throws AccessDeniedException;

	public Page<Order> getOrders(String status, String symbol, Pageable pageable,String authHeader);

	public void updateStatus(Long orderId, String newStatus, String reason);

	Page<Order> getAllOrdersByUserId(Long userId, Pageable pageable);

	List<OrderHistory> getOrderHistory(Long orderId);
}
