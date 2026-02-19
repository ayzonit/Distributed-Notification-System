package com.project.user_service.payload.UserPreference;

import com.project.shared.events.NotificationChannel;
import com.project.shared.events.NotificationPriority;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class UserPreferenceResponseDTO {

    private Long userId;
    private Map<NotificationPriority, List<NotificationChannel>> preferences;
    private LocalDateTime updatedAt;
}
