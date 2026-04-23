# Distributed Notification System

An event-driven notification system built using Kafka, designed to process high-throughput workloads with low latency and priority-based delivery.

---

## Overview

This project implements a distributed notification system that processes and delivers notifications asynchronously using an event-driven architecture.

The system is designed to handle high volumes of messages reliably while ensuring:

* low latency delivery
* zero message loss
* prioritization of critical notifications

---

## Architecture

<img width="1536" height="1024" alt="dns" src="https://github.com/user-attachments/assets/464201c5-2422-41e2-a1b9-49ce11bcc0ef" />


### Request Flow

1. Producer service publishes notification events to Kafka topics
2. Kafka brokers store and distribute messages across partitions
3. Consumer services process messages asynchronously
4. Redis is used to fetch and cache user preferences
5. Notifications are routed based on priority
6. Processed notifications are persisted in PostgreSQL

---

## Components

### producer-service

Generates and publishes notification events to Kafka.

### notification-service (consumer)

Consumes events from Kafka and processes them asynchronously.

### priority-router

Ensures high-priority notifications are processed ahead of lower priority ones.

### redis-cache

Stores user preferences and frequently accessed data to reduce database load.

### database (PostgreSQL)

Persists notification state and delivery logs.

### docker

Containerized setup for all services.

---

## Tech Stack

* Backend: Java, Spring Boot
* Messaging: Apache Kafka
* Cache: Redis
* Database: PostgreSQL
* Containerization: Docker

---

## Project Structure

```id="w8h5a2"
distributed-notification-system/
│
├── producer-service/
├── notification-service/
├── priority-router/
├── config/
├── docker/
├── tests/
└── docker-compose.yml
```

---

## System Design

### Event-Driven Architecture

* Producers emit events to Kafka topics
* Consumers process events independently
* Services are loosely coupled and horizontally scalable

---

### Priority-Based Routing

* Messages are categorized by priority
* High-priority notifications are processed first
* Ensures critical alerts are delivered within strict latency bounds

---

### Redis Caching

* Stores user preferences and notification settings
* Reduces database load significantly
* Improves response time during message processing

---

## Performance

* Throughput: ~10,000 notifications per minute
* Latency: p95 ~320 ms
* Reliability: zero message loss under sustained load

---

## Setup and Installation

### Prerequisites

* Java 17+
* Docker
* Kafka (or use Docker Compose)

---

### Run using Docker Compose

```bash id="l9r3xt"
docker-compose up --build
```

---

### Run manually

```bash id="7n8d5k"
git clone https://github.com/your-username/distributed-notification-system.git
cd distributed-notification-system
```

Start Kafka and Redis, then run services individually.

---

## API Behavior

### Notification Event

* Produced to Kafka topic

### Processing

* Consumed asynchronously
* Routed based on priority

### Persistence

* Stored in PostgreSQL

---

## Design Decisions

### 1. Kafka for Messaging

* High throughput and durability
* Partitioning enables parallel processing
* Decouples producers and consumers

---

### 2. Priority Handling

* Ensures time-sensitive notifications are not delayed
* Prevents queue starvation for critical events

---

### 3. Redis for Caching

* Reduces repeated database lookups
* Improves system performance under load

---

## Future Improvements

* Dead-letter queues for failed messages
* Retry mechanisms with exponential backoff
* Observability (Prometheus + Grafana)
* Distributed tracing (OpenTelemetry)
* Multi-region deployment

---

## Author
