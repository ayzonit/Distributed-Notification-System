package com.project.user_service.service.Interface;

import com.project.user_service.models.User;
import com.project.user_service.payload.User.UserRequestDTO;
import com.project.user_service.payload.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long userId);
    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO);
    void deleteUser(Long userId);
}
