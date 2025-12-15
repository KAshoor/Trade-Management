package com.example.temporal.dto;

import java.math.BigDecimal;

public class ResponseResult {
	BigDecimal executionPrice;
	int quantity;

	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseResult(BigDecimal executionPrice, int quantity) {
		super();
		this.executionPrice = executionPrice;
		this.quantity = quantity;
	}

	public BigDecimal getExecutionPrice() {
		return executionPrice;
	}

	public void setExecutionPrice(BigDecimal executionPrice) {
		this.executionPrice = executionPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
