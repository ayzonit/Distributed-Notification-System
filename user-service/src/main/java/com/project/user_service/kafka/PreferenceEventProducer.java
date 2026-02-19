package com.project.user_service.kafka;

import com.project.shared.events.UserPreferenceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PreferenceEventProducer {

    @Autowired
    private KafkaTemplate<Long, UserPreferenceEvent> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PreferenceEventProducer.class);

    private static final String TOPIC = "user-preferences-updated";

    public void createPreferenceEvent(UserPreferenceEvent event) {
        kafkaTemplate.send(TOPIC, event.getUserId(), event);
        logger.info("Published preference event for userId={}", event.getEventId());
    }
}