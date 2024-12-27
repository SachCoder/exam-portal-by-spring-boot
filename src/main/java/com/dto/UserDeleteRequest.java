package com.dto;


import lombok.Data;

@Data
public class UserDeleteRequest {
    private String email;
    private String password;
}
