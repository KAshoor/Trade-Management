package com.example.temporal.workflow;

import java.time.Duration;

import com.example.temporal.activities.TradeActivities;
import com.example.temporal.dto.OrderRequest;
import com.example.temporal.dto.ResponseResult;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

public class OrderWorkflowImpl implements OrderWorkflow {

	private final TradeActivities activities = Workflow
			.newActivityStub(TradeActivities.class,
					ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10))
							.setRetryOptions(io.temporal.common.RetryOptions.newBuilder()
									.setInitialInterval(Duration.ofSeconds(1)).setBackoffCoefficient(2)
									.setMaximumAttempts(3).build())
							.build());

	@Override
	public String processOrder(OrderRequest orderRequest) {
		Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(false).build();

		Saga saga = new Saga(sagaOptions);

		try {
			boolean valid = activities.validateOrder(orderRequest);
			if (!valid)
				return "Validation failed";

			boolean fraudPassed = activities.fraudCheck(orderRequest);

			if (!fraudPassed) {
				return "REJECTED: Fraud detected";
			}

			ResponseResult executeOrder = activities.executeOrder(orderRequest);
			saga.addCompensation(() -> activities.cancelExecuteOrder(orderRequest));

			activities.settleOrder(orderRequest);
			saga.addCompensation(() -> activities.cancelSettleOrder(orderRequest));

			return "Order completed successfully: " + orderRequest.getSymbol();

		} catch (Exception e) {
			Workflow.getLogger(this.getClass()).error("Workflow failed: " + e.getMessage());
			saga.compensate(); // Trigger all compensations
			return "Order failed and compensated: " + e.getMessage();
		}
	}

}
