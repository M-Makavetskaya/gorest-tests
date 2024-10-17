package com.gorest.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    String id;
    String name;
    String gender;
    String email;
    String status;

    public User(String name, String gender, String email,  String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }
}
