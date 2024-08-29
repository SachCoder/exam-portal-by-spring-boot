package dto;

import lombok.Data;

@Data
public class UserCreateDto {
private String id;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String otp;
    private String status;

}
