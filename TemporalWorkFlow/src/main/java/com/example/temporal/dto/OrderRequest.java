package com.example.temporal.dto;

import java.math.BigDecimal;

public class OrderRequest {

	private String symbol;
    
	private Long userId;

	private String side;

	private int quantity;
	private String orderType;
	private BigDecimal limitPrice;
    private String token;

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderRequest(String symbol, Long userId,String side,
			int quantity, String orderType, BigDecimal limitPrice) {
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	

}
