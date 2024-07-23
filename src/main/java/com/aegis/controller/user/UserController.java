package com.aegis.controller.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.controller.user.request.UpdateUserRequest;
import com.aegis.response.ResponseAPI;
import com.aegis.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @PostMapping()
    public ResponseEntity<ResponseAPI> createUser(@Valid @RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<ResponseAPI> updateUser(
            @PathVariable(name = "user_id") String userId,
            @Valid @RequestBody UpdateUserRequest request) {
        return service.updateUser(userId, request);
    }

    @GetMapping()
    public ResponseEntity<ResponseAPI> listUser() {
        return service.listUser();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<ResponseAPI> detailUser(@PathVariable(name = "user_id") String userId) {
        return service.detailUser(userId);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ResponseAPI> deleteUser(@PathVariable(name = "user_id") String userId) {
        return service.deleteUser(userId);
    }

}
