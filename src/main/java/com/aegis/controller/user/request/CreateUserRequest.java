package com.aegis.controller.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String fullName;

    @Email(message = "Email pattern not valid.")
    @NotBlank(message = "Email cannot be blank or null.")
    private String email;

    private String role;
}
