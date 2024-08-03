package com.example.warehouse.model;

//need to shift this class to a separate package

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
}

// since DTOs are typically used for data transfer, they shudnt contain business logic
// Getters and setters aahhhhhhhhhhh

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
