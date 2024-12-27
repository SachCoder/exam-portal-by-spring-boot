package com.dto;

import lombok.Data;

@Data
public class UserVerifyDto {

public UserVerifyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	//    private String action;
    private String otp;
    private String status;
	public UserVerifyDto(String otp, String status) {
		super();
		this.otp = otp;
		this.status = status;
	}

}
