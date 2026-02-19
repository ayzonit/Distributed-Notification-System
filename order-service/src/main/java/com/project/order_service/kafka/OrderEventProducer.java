package com.project.order_service.kafka;


import com.project.shared.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "order-created-topic";

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        logger.info("Publishing OrderEventCreated with eventId {}", event.getEventId());
        kafkaTemplate.send(TOPIC, event.getOrderId(), event);
    }
}
