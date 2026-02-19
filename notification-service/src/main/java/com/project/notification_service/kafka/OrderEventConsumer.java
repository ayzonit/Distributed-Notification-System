package com.project.notification_service.kafka;

import com.project.notification_service.service.router.NotificationRouter;
import com.project.shared.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderEventConsumer.class);

    @Autowired
    NotificationRouter notificationRouter;

    @KafkaListener(topics = "order-created-topic", groupId = "notification-group")
    public void consume(OrderCreatedEvent event) {

        logger.info("Received OrderCreatedEvent: eventId {}, orderId {}",
                event.getEventId(), event.getOrderId());

        notificationRouter.routeNotification(event);

        logger.info("Event routed based on priority: {}", event.getPriority());
    }
}
