package org.com.orderservice.dto;

import java.time.LocalDateTime;

public class OrderResponse {
	private Long orderId;
	private String status;
	private LocalDateTime createdAt;
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderResponse(Long orderId, String status, LocalDateTime createdAt) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.createdAt = createdAt;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
