package com.aegis.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import static com.aegis.util.constant.Constant.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "role")
    private String role;

    @PrePersist
    public void prePersist() {
        if (this.role == null || this.role.isEmpty()) {
            this.role = USER_ROLE;
        }
    }
}