package com.aegis.controller.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.response.ResponseAPI;
import com.aegis.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @PostMapping()
    public ResponseEntity<ResponseAPI> createUser(@Valid @RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

}
