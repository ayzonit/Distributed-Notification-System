package com.project.notification_service.controllers;


import com.project.notification_service.payload.NotificationRequest;
import com.project.notification_service.payload.NotificationResponse;
import com.project.notification_service.service.Interface.NotificationService;
import com.project.shared.events.NotificationPriority;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationByUser(
           @PathVariable String userId) {
        List<NotificationResponse> user = notificationService.findByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/priority/{notificationPriority}")
    public ResponseEntity<List<NotificationResponse>>
    getUserNotificationByPriority(@PathVariable String userId,
                                  @PathVariable NotificationPriority notificationPriority) {
        List<NotificationResponse> user = notificationService.findByUserIdAndPriorirty(userId, notificationPriority);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <NotificationResponse> create(@RequestBody @Valid NotificationRequest notification) {
        NotificationResponse newNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }
}
