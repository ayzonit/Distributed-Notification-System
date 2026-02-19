package com.project.notification_service.kafka;

import com.project.notification_service.service.cache.UserPreferenceCacheService;
import com.project.shared.events.UserPreferenceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserPreferenceEventConsumer {

    @Autowired
    UserPreferenceCacheService userPreferenceCacheService;

    @KafkaListener(topics = "user-preference-topic", groupId = "notification-service-group")
    public void consume(UserPreferenceEvent event) {
        userPreferenceCacheService.savePreferences(event.getEventId(), event.getUserPreferences());
    }
}
