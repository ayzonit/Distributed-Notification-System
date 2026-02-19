package com.project.notification_service.service.sender;

import com.project.notification_service.service.Interface.NotificationSenderService;
import com.project.shared.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PushSenderServiceImpl implements NotificationSenderService {

    private static final Logger logger = LoggerFactory.getLogger(PushSenderServiceImpl.class);

    @Override
    public void send(OrderCreatedEvent event) {
        logger.info("Push notification send for order: {}", event.getOrderId());
    }
}
