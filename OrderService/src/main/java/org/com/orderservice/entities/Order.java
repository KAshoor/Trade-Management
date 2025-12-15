package org.com.orderservice.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private Long userId;
	private String symbol;
	private String side;
	private int quantity;
	private String orderType;
	@Column(precision = 38, scale = 2)
	private BigDecimal limitPrice;
	private String status;

	@Column(columnDefinition = "DATETIME")
	private LocalDateTime createdAt;
	@Column(columnDefinition = "DATETIME")
	private LocalDateTime updatedAt;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, Long userId, String symbol, String side, int quantity, String orderType,
			BigDecimal limitPrice, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.symbol = symbol;
		this.side = side;
		this.quantity = quantity;
		this.orderType = orderType;
		this.limitPrice = limitPrice;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", symbol=" + symbol + ", side=" + side
				+ ", quantity=" + quantity + ", orderType=" + orderType + ", limitPrice=" + limitPrice + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	

}
