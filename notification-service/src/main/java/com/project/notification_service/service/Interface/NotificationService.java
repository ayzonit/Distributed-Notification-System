package com.project.notification_service.service.Interface;

import com.project.notification_service.payload.NotificationRequest;
import com.project.notification_service.payload.NotificationResponse;
import com.project.shared.events.NotificationPriority;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest notificationRequest);
    List<NotificationResponse> findByUserId(String userId);
    List<NotificationResponse> findByUserIdAndPriorirty(String userId, NotificationPriority notificationPriority);
}
