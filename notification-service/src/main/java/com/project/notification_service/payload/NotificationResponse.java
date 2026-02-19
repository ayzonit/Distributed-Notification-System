package com.project.notification_service.payload;


import com.project.shared.events.NotificationPriority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private Long notificationId;

    private String userId;

    private String type;

    private NotificationPriority priority;

    private String title;

    private String message;

    private LocalDateTime createdAt;

}

