package com.service;

import com.entities.User;

import dto.UserCreateDto;
import dto.UserDeleteRequest;
import dto.UserVerifyDto;

public interface UserService {
	public User findByEmail(String email);

	public void add(UserCreateDto newUser);

	public void authDeleteByUser(UserDeleteRequest userDeleteRequest);
	
	public User getById(String id);
	
	public User getByUsername(String email);

	public String verify(String email, String otp);

	public UserVerifyDto resendOtp(String email);

	 

 
}
