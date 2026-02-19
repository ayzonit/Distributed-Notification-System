package com.project.order_service.services;
import com.project.order_service.kafka.OrderEventProducer;
import com.project.order_service.models.OrderModel;
import com.project.order_service.models.Status;
import com.project.order_service.repository.OrderRepository;
import com.project.shared.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEventProducer orderEventProducer;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<OrderModel> getOrdersByUserId(String userId) {
        logger.info("Received getOrdersByUserId Request : {}", userId);
        List<OrderModel> order = orderRepository.findByUserId(userId);
        return order;
    }

    @Override
    public OrderModel createOrder(OrderModel order) {
        logger.info("Received createOrder request from user: {}", order.getUserId());

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Status.Placed);

        OrderModel savedOrder = orderRepository.save(order);
        logger.info("Saved Order ID: {}", savedOrder.getOrderid());

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(savedOrder.getOrderid())
                .userId(savedOrder.getUserId())
                .status(savedOrder.getStatus().name())
                .createdAt(savedOrder.getCreatedAt())
                .build();
        orderEventProducer.sendOrderCreatedEvent(event);
        logger.info("Published OrderCreatedEvent for orderId: {}", savedOrder.getOrderid());
        return savedOrder;
    }
}
