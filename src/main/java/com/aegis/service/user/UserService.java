package com.aegis.service.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.controller.user.request.UpdateUserRequest;
import com.aegis.response.ResponseAPI;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseAPI> createUser(CreateUserRequest request);
    ResponseEntity<ResponseAPI> updateUser(String userId, UpdateUserRequest request);
    ResponseEntity<ResponseAPI> getListUser();
    ResponseEntity<ResponseAPI> detailUser(String userId);
    ResponseEntity<ResponseAPI> deleteUser(String userId);
}
