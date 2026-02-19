package com.project.notification_service.service.Interface;

import com.project.shared.events.OrderCreatedEvent;

public interface NotificationSenderService {
    void send(OrderCreatedEvent orderCreatedEvent);
}
