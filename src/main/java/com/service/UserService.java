package com.service;

import com.dto.UserCreateDto;
import com.dto.UserDeleteRequest;
import com.dto.UserVerifyDto;
import com.entities.User;

public interface UserService {
	public User findByEmail(String email);

	public void add(UserCreateDto newUser);

	public void authDeleteByUser(UserDeleteRequest userDeleteRequest);
	
	public User getById(String id);
	
	public User getByUsername(String email);

	public String verify(String email, String otp);

	public UserVerifyDto resendOtp(String email);

	 

 
}
