The Order Management System is a Spring Boot-based backend application for managing users and trade orders.
It supports secure authentication, order state persistence, and asynchronous order processing using Temporal workflows.
The system implements the Saga pattern to ensure reliable transaction processing and automatic compensation on failures.

using docker compose for temporal server.
Workflow Steps:
Validate order parameters
Fraud check
Execute order
Settle order
Saga Pattern ensures compensating transactions for failed steps.
Orders are persisted in the database at each step for real-time status tracking.
