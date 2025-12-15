package com.example.temporal.activities;

import java.math.BigDecimal;

import com.example.temporal.dto.OrderRequest;
import com.example.temporal.dto.OrderResponse;
import com.example.temporal.dto.ResponseResult;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TradeActivities {
	@ActivityMethod
	boolean validateOrder(OrderRequest request);

	@ActivityMethod
	boolean fraudCheck(OrderRequest request);

	@ActivityMethod
	ResponseResult executeOrder(OrderRequest request);

	@ActivityMethod
	void cancelExecuteOrder(OrderRequest request);

	void settleOrder(OrderRequest request);

	@ActivityMethod
	void cancelSettleOrder(OrderRequest request);

	@ActivityMethod
	void compensateOrder(Long orderId);
}
