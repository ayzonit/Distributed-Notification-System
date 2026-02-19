package com.project.user_service.payload.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
}
