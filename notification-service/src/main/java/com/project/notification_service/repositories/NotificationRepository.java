package com.project.notification_service.repositories;

import com.project.notification_service.model.NotificationModel;
import com.project.shared.events.NotificationPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

    List<NotificationModel> findByUserId(String userId);
    List<NotificationModel> findByUserIdAndPriority(String userId,
                                                          NotificationPriority notificationPriority);
}
