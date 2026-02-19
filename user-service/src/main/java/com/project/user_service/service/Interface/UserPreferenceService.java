package com.project.user_service.service.Interface;

import com.project.user_service.payload.UserPreference.UserPreferenceRequestDTO;
import com.project.user_service.payload.UserPreference.UserPreferenceResponseDTO;

public interface UserPreferenceService {

    UserPreferenceResponseDTO updatePreference(Long userId, UserPreferenceRequestDTO userPreferenceRequestDTO);
    UserPreferenceResponseDTO getPreferences(Long userId);
    void deletePreferences(Long userId);
}
