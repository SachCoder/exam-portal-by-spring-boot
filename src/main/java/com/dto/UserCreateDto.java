package com.dto;

 
import lombok.Data;

@Data
 
public class UserCreateDto {
private String id;
    private String username;
//    @JsonIgnore
    private String password;
    private String mobile;
    private String email;
 

}
