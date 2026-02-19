package com.project.notification_service.ApiClient;

import com.project.shared.events.NotificationChannel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8082")
public interface UserServiceClient {

    @GetMapping("/api/preferences/{userId}")
    List<NotificationChannel> fetchPreferences(@PathVariable String userId);
}
