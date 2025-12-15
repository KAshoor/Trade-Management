package com.example.temporal.config;

import org.springframework.context.annotation.Bean;

import com.example.temporal.activities.TradeActivitiesImpl;
import com.example.temporal.workflow.OrderWorkflowImpl;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;

public class TemporalConfig {
	 @Bean
	    public WorkerFactory workerFactory(WorkflowServiceStubs serviceStubs) {
	        WorkflowClient client = WorkflowClient.newInstance(serviceStubs);
	        WorkerFactory factory = WorkerFactory.newInstance(client);

	        Worker worker = factory.newWorker("TRAVEL_TASK_QUEUE");
	        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
	        worker.registerActivitiesImplementations(new TradeActivitiesImpl());

	        return factory;
	    }

	    @Bean
	    public WorkflowServiceStubs serviceStubs() {
	        return WorkflowServiceStubs.newInstance();
	    }

	    @PostConstruct
	    public void startWorker() {
	        workerFactory(serviceStubs()).start();
	    }
}
