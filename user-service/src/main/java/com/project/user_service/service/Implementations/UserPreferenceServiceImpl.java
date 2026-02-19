package com.project.user_service.service.Implementations;

import com.project.shared.events.UserPreferenceEvent;
import com.project.user_service.kafka.PreferenceEventProducer;
import com.project.user_service.models.UserPreference;
import com.project.user_service.payload.UserPreference.UserPreferenceRequestDTO;
import com.project.user_service.payload.UserPreference.UserPreferenceResponseDTO;
import com.project.user_service.repository.PreferenceRepository;
import com.project.user_service.service.Interface.UserPreferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PreferenceEventProducer preferenceEventProducer;

    @Override
    public UserPreferenceResponseDTO updatePreference(Long userId, UserPreferenceRequestDTO userPreferenceRequestDTO) {
        UserPreference userPreference = preferenceRepository.findById(userId)
                .orElseGet(() -> {
                    UserPreference newPreference = new UserPreference();
                    newPreference.setUserId(userId);
                    return newPreference;
                });
        userPreference.setUserId(userId);
        userPreference.setPreferences(userPreferenceRequestDTO.getPreferences());

        UserPreference savedPreference = preferenceRepository.save(userPreference);

        UserPreferenceEvent event = new UserPreferenceEvent(userId, userPreferenceRequestDTO.getPreferences());
        preferenceEventProducer.createPreferenceEvent(event);
        return modelMapper.map(savedPreference, UserPreferenceResponseDTO.class);
    }

    @Override
    public UserPreferenceResponseDTO getPreferences(Long userId) {
        UserPreference preference = preferenceRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));
        return modelMapper.map(preference, UserPreferenceResponseDTO.class);
    }

    @Override
    public void deletePreferences(Long userId) {
        if(!preferenceRepository.existsById(userId)) {
            throw new RuntimeException("Preferences not found");
        }
        preferenceRepository.deleteById(userId);
    }
}
