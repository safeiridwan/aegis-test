package com.aegis.controller.auth;

import com.aegis.controller.auth.request.LoginRequest;
import com.aegis.response.ResponseAPI;
import com.aegis.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<ResponseAPI> login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseAPI> resetPassword() {
        return service.resetPassword();
    }
}
