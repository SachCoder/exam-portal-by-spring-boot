package com.entities;

import lombok.Data;

@Data
public class JwtResponse {
    String token;
    String userRole;

    public JwtResponse() {
    }

    public JwtResponse(String token, String userRole) {
        this.token = token;
        this.userRole = userRole ;
    }

    
}
