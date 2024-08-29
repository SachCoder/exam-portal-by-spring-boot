package dto;

import lombok.Data;

@Data
public class UserVerifyDto {

    private String action;
    private String otp;
    private String status;

}
