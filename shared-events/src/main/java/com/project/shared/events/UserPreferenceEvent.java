package com.project.shared.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPreferenceEvent {
    private Long userId;
    private Map<NotificationPriority, List<NotificationChannel>> userPreferences;
    private String eventId;
    private String eventType;
    private LocalDateTime timestamp;
    public UserPreferenceEvent(Long userId, Map<NotificationPriority, List<NotificationChannel>> preferences) {
    }
}
