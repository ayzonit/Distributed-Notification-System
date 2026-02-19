package com.project.notification_service.payload;


import com.project.shared.events.NotificationPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String type;

    @NotNull
    private NotificationPriority priority;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

}

