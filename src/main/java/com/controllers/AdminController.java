package com.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDeleteRequest;
import com.service.UserService;
import com.shared.BiometricMessage;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private UserService userService; 
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("data")
	public String admin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		return "hello admin";
	}
	
	 @DeleteMapping("/deleteByUser")
	    public ResponseEntity<?> deleteByUser(@RequestBody UserDeleteRequest userDeleteRequest) {
	        userService.authDeleteByUser(userDeleteRequest);
//	        sendEmailService.sendEmails(userDeleteRequest.getEMail(), ECommerceMessage.AUTH_DELETE_BODY, ECommerceMessage.AUTH_DELETE_TOPIC + ECommerceMessage.AUTH_DELETE_TOPIC_EMOJI);
	        return new ResponseEntity<>(BiometricMessage.USER_DELETED, HttpStatus.OK);
	    }
	
	
}
 
