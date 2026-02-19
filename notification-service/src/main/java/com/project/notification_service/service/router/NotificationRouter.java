package com.project.notification_service.service.router;

import com.project.notification_service.service.cache.UserPreferenceCacheService;
import com.project.notification_service.service.sender.EmailSenderServiceImpl;
import com.project.notification_service.service.sender.PushSenderServiceImpl;
import com.project.notification_service.service.sender.SmsSenderServiceImpl;
import com.project.shared.events.NotificationChannel;
import com.project.shared.events.NotificationPriority;
import com.project.shared.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationRouter {

    @Autowired
    PushSenderServiceImpl pushSenderService;

    @Autowired
    EmailSenderServiceImpl emailSenderService;

    @Autowired
    SmsSenderServiceImpl smsSenderService;

    @Autowired
    UserPreferenceCacheService userPreferenceCacheService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationRouter.class);

    public void routeNotification(OrderCreatedEvent event) {

        NotificationPriority priority = event.getPriority();
        String userId = event.getUserId();

        logger.info("Routing notification for order {} with priority {}", event.getOrderId(), event.getPriority());

        List<NotificationChannel> userPreferences = userPreferenceCacheService.getPreferences(userId);
        switch (priority) {

            case CRITICAL -> {
                smsSenderService.send(event);
                emailSenderService.send(event);
                pushSenderService.send(event);
            }

            case STANDARD -> {
                if (userPreferences.contains(NotificationChannel.Push)) {
                    pushSenderService.send(event);
                }
                else {
                    logger.info("User {} has disabled Push notifications", userId);
                }
                if (userPreferences.contains(NotificationChannel.Email))
                    emailSenderService.send(event);
                else {
                    logger.info("User {} has disabled Email notifications", userId);
                }
            }

            case PROMOTIONAL -> {
                if (userPreferences.contains(NotificationChannel.Email)) {
                    emailSenderService.send(event);
                } else {
                    logger.info("User {} has disabled Email notifications", userId);
                }
            }
            default -> throw new IllegalArgumentException("Unknown notification priority: " + priority);
        }
    }
}
