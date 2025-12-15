package org.com.orderservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderRequest {

	@NotBlank
	private String symbol;
    
	@NotBlank
	private Long userId;

	@Pattern(regexp = "BUY|SELL")
	private String side;


	@Min(1)
	private int quantity;


	@Pattern(regexp = "MARKET|LIMIT")
	private String orderType;


	private BigDecimal limitPrice;


	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderRequest(@NotBlank String symbol, @NotBlank Long userId, @Pattern(regexp = "BUY|SELL") String side,
			@Min(1) int quantity, @Pattern(regexp = "MARKET|LIMIT") String orderType, BigDecimal limitPrice) {
		super();
		this.symbol = symbol;
		this.userId = userId;
		this.side = side;
		this.quantity = quantity;
		this.orderType = orderType;
		this.limitPrice = limitPrice;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
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


	
	
	
}
