package com.project.user_service.payload.UserPreference;

import com.project.shared.events.NotificationChannel;
import com.project.shared.events.NotificationPriority;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserPreferenceRequestDTO {
    private Map<NotificationPriority, List<NotificationChannel>> preferences;
}
