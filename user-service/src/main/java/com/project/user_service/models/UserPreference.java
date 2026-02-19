package com.project.user_service.models;

import com.project.shared.events.NotificationChannel;
import com.project.shared.events.NotificationPriority;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user_preferences")
@Data
public class UserPreference {

    @Id
    private Long userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_preference_channels", joinColumns = @JoinColumn(name = "user_Id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Map<NotificationPriority, List<NotificationChannel>> preferences;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
