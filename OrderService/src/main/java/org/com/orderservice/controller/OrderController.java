package org.com.orderservice.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.com.orderservice.dto.OrderRequest;
import org.com.orderservice.dto.OrderResponse;
import org.com.orderservice.dto.UserDto;
import org.com.orderservice.entities.Order;
import org.com.orderservice.entities.OrderHistory;
import org.com.orderservice.repositories.OrderRepository;
import org.com.orderservice.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid OrderRequest req,@RequestHeader("Authorization") String authHeader) throws AccessDeniedException {
		OrderResponse orderResponse = service.createOrder(req,authHeader);
		if (orderResponse == null)
			return new ResponseEntity<>("Something went wrong..", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}
	
	  @GetMapping("/{orderId}")
	    public ResponseEntity<?> getOrder(@PathVariable Long orderId, @RequestHeader("Authorization") String authHeader) throws AccessDeniedException {
		  OrderResponse orderResponse = service.getOrder(orderId,authHeader);
			if (orderResponse == null)
				return new ResponseEntity<>("Something went wrong..", HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	    }
	

	  @GetMapping
	    public Page<Order> getOrders(@RequestParam(required = false) String status,
	                                 @RequestParam(required = false) String symbol,
	                                 @RequestParam(defaultValue = "0") int page,
	                                 @RequestParam(defaultValue = "10") int size,
	                                 @RequestHeader("Authorization") String authHeader) {
	       
	        return service.getOrders(status, symbol, PageRequest.of(page, size),authHeader);
	    }

	@GetMapping("/history/{orderId}")
	public List<OrderHistory> history(@PathVariable Long orderId) {
		return service.getOrderHistory(orderId);
	}
}
