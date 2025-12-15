package org.com.orderservice.servicesimpls;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.com.orderservice.dto.OrderRequest;
import org.com.orderservice.dto.OrderResponse;
import org.com.orderservice.entities.Order;
import org.com.orderservice.entities.OrderHistory;
import org.com.orderservice.exception.OrderNotFoundException;
import org.com.orderservice.repositories.OrderHistoryRepository;
import org.com.orderservice.repositories.OrderRepository;
import org.com.orderservice.services.OrderService;
import org.com.orderservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Objects;
import jakarta.validation.ValidationException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderHistoryRepository historyRepo;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public OrderResponse createOrder(OrderRequest req, String authHeader) throws AccessDeniedException {
		validate(req);
		String token = authHeader.substring(7);
		Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		String role = jwtUtil.extractRole(token);
		if (!"TRADER".equals(role)) {
			throw new AccessDeniedException("Only TRADER can create orders");
		}
		Order order = new Order();
		order.setUserId(userId);
		order.setSymbol(req.getSymbol());
		order.setSide(req.getSide());
		order.setQuantity(req.getQuantity());
		order.setOrderType(req.getOrderType());
		order.setLimitPrice(req.getLimitPrice());
		order.setStatus("PENDING");
		order.setCreatedAt(LocalDateTime.now());

		orderRepo.save(order);
		audit(order.getOrderId(), null, "PENDING", "Order created");
		return map(order);
	}

	@Override
	public OrderResponse getOrder(Long orderId, String authHeader) throws AccessDeniedException {
		String token = authHeader.substring(7);
		Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		String role = jwtUtil.extractRole(token);
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order did not find !!! id = " + orderId));
		if (!role.equals("ADMIN") && !order.getUserId().equals(userId)) {
			throw new AccessDeniedException("Not allowed to access this order");
		}
		return map(order);
	}

	@Override
	public Page<Order> getOrders(String status, String symbol, Pageable pageable, String authHeader) {
		String token = authHeader.substring(7);
		Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		String role = jwtUtil.extractRole(token);
		if (!"ADMIN".equals(role)) {
			if (status != null && symbol != null) {
				return orderRepo.findByUserIdAndStatusAndSymbol(userId, status, symbol, pageable);
			} else if (status != null) {
				return orderRepo.findByUserIdAndStatus(userId, status, pageable);
			} else {
				return orderRepo.findByUserId(userId, pageable);
			}
		}

		if (status != null && symbol != null) {
			return orderRepo.findByStatusAndSymbol(status, symbol, pageable);
		} else if (status != null) {
			return orderRepo.findByStatus(status, pageable);
		}

		return orderRepo.findAll(pageable);
	}

	private void validate(OrderRequest req) {
		if (req.getOrderType().equals("LIMIT") && req.getLimitPrice() == null)
			throw new ValidationException("Limit price required for LIMIT order");
	}

	@Override
	public void updateStatus(Long orderId, String newStatus, String reason) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order did not find !!! id = " + orderId));

		audit(orderId, order.getStatus(), newStatus, reason);
		order.setStatus(newStatus);
		order.setUpdatedAt(LocalDateTime.now());
		orderRepo.save(order);

	}

	private void audit(Long orderId, String from, String to, String reason) {
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setOrderId(orderId);
		orderHistory.setFromState(from);
		orderHistory.setToState(to);
		orderHistory.setReason(reason);
		orderHistory.setChangedAt(LocalDateTime.now());
		historyRepo.save(orderHistory);
	}

	private OrderResponse map(Order o) {
		OrderResponse r = new OrderResponse();
		r.setOrderId(o.getOrderId());
		r.setStatus(o.getStatus());
		r.setCreatedAt(o.getCreatedAt());
		return r;

	}

	@Override
	public Page<Order> getAllOrdersByUserId(Long userId, Pageable pageable) {
		return orderRepo.findByUserId(userId, pageable);
	}

	@Override
	public List<OrderHistory> getOrderHistory(Long orderId) {
		return this.historyRepo.findByOrderId(orderId);
	}

}
