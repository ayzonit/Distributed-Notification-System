package com.project.shared.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {

    private String eventId;
    private String orderId;
    private String userId;
    private String status;
    private LocalDateTime createdAt;
    private NotificationPriority priority;

}
