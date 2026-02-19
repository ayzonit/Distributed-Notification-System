package com.project.notification_service.model;

import com.project.shared.events.NotificationPriority;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String userId;

    private String type;

    @Enumerated(EnumType.STRING)
    private NotificationPriority priority;

    private String title;

    private String message;

    private LocalDateTime createdAt;
}
