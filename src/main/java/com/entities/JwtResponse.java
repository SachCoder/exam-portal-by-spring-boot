package com.entities;

import lombok.Data;

@Data
public class JwtResponse {
    String token;
    

    public JwtResponse() {
    }

    public JwtResponse(String token ) {
        this.token = token;
        
    }

    
}
