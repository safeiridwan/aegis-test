package com.aegis.service;

import com.aegis.controller.auth.request.LoginRequest;
import com.aegis.response.ResponseAPI;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseAPI> login(LoginRequest request);
}
