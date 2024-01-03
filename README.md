# Microbank

## Overview

This Microbank project is a robust and scalable application consisting of loosely coupled Spring Boot microservices that comply to the CQRS and Event Sourcing patterns.

## Features
- **Microservices Architecture:** The system is built as a set of microservices, enabling scalability, maintainability, and independent deployment of different components.
- **Service Discovery:** Eureka allows services to dynamically discover and communicate with each other, improving system resilience.
- **API Gateway**: Spring Cloud Gateway serves as a single entry point for external requests, simplifying routing.
- **Event-Driven Processing:** Kafka facilitates real-time communication between services, ensuring efficient data synchronization.
