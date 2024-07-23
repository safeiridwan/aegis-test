package com.aegis.controller.user.response;

import com.aegis.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailUserResponse {
    private String userId;
    private String fullName;
    private String email;
    private String role;

    public DetailUserResponse() {
    }

    public DetailUserResponse(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.role = user.getRole();
    }
}
