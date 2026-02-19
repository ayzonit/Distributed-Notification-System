package com.project.notification_service.service.cache;

import com.project.notification_service.ApiClient.UserServiceClient;
import com.project.shared.events.NotificationChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferenceCacheService {

    @Autowired
    UserServiceClient userServiceClient;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserPreferenceCacheService.class);

    private static final String KEY_PREFIX = "user:preferences:";

    public List<NotificationChannel> getPreferences(String userId) {
        String key = KEY_PREFIX + userId;
        Object cached = redisTemplate.opsForValue().get(key);
        if(cached != null) {
            return (List<NotificationChannel>) cached;
        }

        logger.info("Fetching user preferences for user {}", userId);
        List<NotificationChannel> preferences = userServiceClient.fetchPreferences(userId);
        logger.info("Fetched user preference {} for user {}", preferences, userId);

        if (preferences != null && !preferences.isEmpty()) {
            redisTemplate.opsForValue().set(key, preferences);
        }

        if (preferences != null) return preferences;
        else {
            return List.of();
        }
    }

    public void savePreferences(String userId, List<NotificationChannel> preferences) {
        redisTemplate.opsForValue().set(KEY_PREFIX + userId, preferences);
    }

    public void deletePreferences(String userId) {
        redisTemplate.delete(KEY_PREFIX + userId);
    }
}
