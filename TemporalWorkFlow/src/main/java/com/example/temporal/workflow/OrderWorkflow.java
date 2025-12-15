package com.example.temporal.workflow;

import com.example.temporal.dto.OrderRequest;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {
	@WorkflowMethod
	String processOrder(OrderRequest orderRequest);
}
