package com.aegis.service.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.response.ResponseAPI;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseAPI> createUser(CreateUserRequest request);
}
