package com.project.user_service.payload.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;
}
