package com.project.user_service.controller;

import com.project.user_service.payload.UserPreference.UserPreferenceRequestDTO;
import com.project.user_service.payload.UserPreference.UserPreferenceResponseDTO;
import com.project.user_service.service.Interface.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    @Autowired
    UserPreferenceService userPreferenceService;

    @PutMapping("/{userId}")
    public ResponseEntity<UserPreferenceResponseDTO> updatePreference(@PathVariable Long userId,
                                                                      @RequestBody UserPreferenceRequestDTO requestDTO) {
        UserPreferenceResponseDTO updatedPreference = userPreferenceService.updatePreference(userId, requestDTO);
        return new ResponseEntity<>(updatedPreference, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreferenceResponseDTO> getPreferences(@PathVariable Long userId) {
        UserPreferenceResponseDTO user = userPreferenceService.getPreferences(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePreferences(@PathVariable Long userId) {
        userPreferenceService.deletePreferences(userId);
        return ResponseEntity.noContent().build();
    }
}
