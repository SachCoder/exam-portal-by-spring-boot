package com.controllers;

 

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repos.UserRepo;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/yes")
	public String hello() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
		System.out.println("yes");
		return "{\"name\":\"Sachin\"}";
		
	}
	  @GetMapping("/getusers")
	    public String getUser() {
	        return "{\"name\":\"Sachin\"}";
	    }
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user/data")
	public String user() {
//		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
//		System.out.println(authentication);
	
		return "hello user";
	}
	
	

	

 
}
