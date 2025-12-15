The Order Management System is a backend application built with Spring Boot that provides:
User authentication and management

Order creation, retrieval, and history tracking

JWT-based authorization

Temporal workflow integration for trade order processing

This project is designed to handle secure and reliable trade operations while maintaining proper state transitions.

User Management: Create, update, delete users with roles.

Authentication: JWT-based authentication and authorization.

Order Management:

Create new orders

View order details by ID

Paginated retrieval of orders with filtering by status or symbol

View order history

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

