package com.project.user_service.service.Implementations;

import com.project.user_service.models.User;
import com.project.user_service.payload.User.UserRequestDTO;
import com.project.user_service.payload.User.UserResponseDTO;
import com.project.user_service.repository.UserRepository;
import com.project.user_service.service.Interface.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User user = modelMapper.map(userRequestDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}