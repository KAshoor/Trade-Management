package com.example.temporal.activities;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.temporal.dto.MarketPriceResponse;
import com.example.temporal.dto.OrderRequest;
import com.example.temporal.dto.OrderResponse;
import com.example.temporal.dto.ResponseResult;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;

@Service
public class TradeActivitiesImpl implements TradeActivities {

	@Autowired
	private RestTemplate restTemplate;
	private final String MARKET_DATA_URL = "http://localhost:8081/api/v1/market/price/";
	private final String ORDER_SERVICE_URL = "http://localhost:8080/api/v1/orders";

	@Override
	public boolean validateOrder(OrderRequest orderRequest) {
		if (orderRequest.getQuantity() <= 0)
			throw new IllegalArgumentException("Quantity must be > 0");
		if (!orderRequest.getSide().equals("BUY") && !orderRequest.getSide().equals("SELL"))
			throw new IllegalArgumentException("Side must be BUY or SELL");
		try {
			MarketPriceResponse resp = restTemplate.getForObject(MARKET_DATA_URL + orderRequest.getSymbol(),
					MarketPriceResponse.class);
			return resp != null && orderRequest.getQuantity() > 0
					&& (orderRequest.getLimitPrice().compareTo(resp.getLast()) > 0);
		} catch (Exception e) {
			throw new RuntimeException("Market data service unavailable");
		}

	}

	@Override
	public boolean fraudCheck(OrderRequest request) {
		try {
			long delay = 500 + (long) (Math.random() * 1500);
			Thread.sleep(delay);
			return true;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ResponseResult executeOrder(OrderRequest orderRequest) {
		MarketPriceResponse price = restTemplate.getForObject(MARKET_DATA_URL + orderRequest.getSymbol(),
				MarketPriceResponse.class);
		ResponseResult result = new ResponseResult();
		result.setExecutionPrice(price.getLast());
		result.setQuantity(orderRequest.getQuantity());
		return result;
	}

	@Override
	public void cancelExecuteOrder(OrderRequest request) {
		System.out.println(
				"Compensating order execution: " + request.getOrderType() + " refund: " + request.getQuantity());
	}

	@Override
	public void settleOrder(OrderRequest orderRequest) {
		String token = orderRequest.getToken();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + token);
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<OrderRequest> entity = new HttpEntity<>(orderRequest, headers);
	    ResponseEntity<OrderResponse> response = restTemplate.postForEntity(
	            ORDER_SERVICE_URL, entity, OrderResponse.class);

	    if (!response.getStatusCode().is2xxSuccessful()) {
	        throw new RuntimeException("Order settlement failed");
	    }
	}

	@Override
	public void cancelSettleOrder(OrderRequest request) {
		System.out.println("Compensating settlement for order: " + request.getOrderType());

	}

	@Override
	public void compensateOrder(Long orderId) {
		System.out.println("Compensating settlement for order: " + orderId);
	}
	
}
