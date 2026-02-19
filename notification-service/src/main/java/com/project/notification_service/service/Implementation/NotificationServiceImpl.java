package com.project.notification_service.service.Implementation;

import com.project.notification_service.model.NotificationModel;
import com.project.notification_service.payload.NotificationRequest;
import com.project.notification_service.payload.NotificationResponse;

import com.project.notification_service.repositories.NotificationRepository;
import com.project.notification_service.service.Interface.NotificationService;
import com.project.shared.events.NotificationPriority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);


    private NotificationModel toRequest(NotificationRequest notificationRequest) {
        NotificationModel notificationModel = new NotificationModel();

        logger.debug("Converting NotificationRequest to NotificationModel for userId {}",
                notificationRequest.getUserId());

        notificationModel.setUserId(notificationRequest.getUserId());
        notificationModel.setType(notificationRequest.getType());
        notificationModel.setPriority(notificationRequest.getPriority());
        notificationModel.setTitle(notificationRequest.getTitle());
        notificationModel.setMessage(notificationRequest.getMessage());
        notificationModel.setCreatedAt(LocalDateTime.now());

        return notificationModel;
    }

    private NotificationResponse toResponse(NotificationModel notificationModel) {
        NotificationResponse notificationResponse = new NotificationResponse();

        logger.debug("Converting NotificationModel to NotificationResponse for notificationId {}",
                notificationResponse.getNotificationId());

        notificationResponse.setNotificationId(notificationModel.getNotificationId());
        notificationResponse.setUserId(notificationModel.getUserId());
        notificationResponse.setType(notificationModel.getType());
        notificationResponse.setPriority(notificationModel.getPriority());
        notificationResponse.setTitle(notificationModel.getTitle());
        notificationResponse.setMessage(notificationModel.getMessage());
        notificationResponse.setCreatedAt(notificationModel.getCreatedAt());

        return notificationResponse;
    }

    @Override
    public NotificationResponse createNotification(NotificationRequest notificationRequest) {
        NotificationModel notificationModel = new NotificationModel();

        logger.info("Creating notification for userId {}, type {}, priority {}",
                notificationRequest.getUserId(),
                notificationRequest.getType(),
                notificationRequest.getPriority());

        notificationModel.setUserId(notificationRequest.getUserId());
        notificationModel.setType(notificationRequest.getType());
        notificationModel.setPriority(notificationRequest.getPriority());
        notificationModel.setTitle(notificationRequest.getTitle());
        notificationModel.setMessage(notificationRequest.getMessage());
        notificationModel.setCreatedAt(LocalDateTime.now());

        NotificationModel savedNotification = notificationRepository.save(notificationModel);

        logger.info("Notification created for with notificationId {}", savedNotification.getNotificationId());

        return toResponse(savedNotification);
    }

    @Override
    public List<NotificationResponse> findByUserId(String userId) {
        logger.info("Fetching notifications for userId {}", userId);
        List<NotificationModel> notifications = notificationRepository.findByUserId(userId);

        logger.info("Found {} notifications for userId {}", notifications.size(), userId);

        return notifications.stream()
                .map(notificationModel -> toResponse(notificationModel))
                .collect(Collectors.toList());

    }

    @Override
    public List<NotificationResponse> findByUserIdAndPriorirty(String userId, NotificationPriority notificationPriority) {
        logger.info("Fetching notifications for userId {} with priority{}", userId, notificationPriority);

        List<NotificationModel> notifications =
                notificationRepository.findByUserIdAndPriority(userId, notificationPriority);

        logger.info("Found {} notifications for userId {} with priority{}",
                notifications.size(), userId, notificationPriority);

        return notifications.stream()
                .map(notificationModel -> toResponse(notificationModel))
                .collect(Collectors.toList());
    }
}
