package com.example.temporal.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.temporal.activities.TradeActivities;
import com.example.temporal.dto.OrderRequest;
import com.example.temporal.workflow.OrderWorkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

@RestController("/api/v1/orders")
public class OrderController {

	private final WorkflowClient workflowClient;

	public OrderController(WorkflowClient workflowClient) {
		this.workflowClient = workflowClient;
	}

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest request,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
		request.setToken(token);
		OrderWorkflow workflow = workflowClient.newWorkflowStub(OrderWorkflow.class, WorkflowOptions.newBuilder()
				.setWorkflowId("order-workflow-" + UUID.randomUUID()).setTaskQueue("ORDER_TASK_QUEUE").build());

		WorkflowClient.start(workflow::processOrder, request);

		return ResponseEntity.ok(request);
	}

}
