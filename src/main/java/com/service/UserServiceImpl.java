package com.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.UserCreateDto;
import com.dto.UserDeleteRequest;
import com.dto.UserVerifyDto;
import com.entities.Role;
import com.entities.User;
import com.extras.EmailSender;
import com.extras.OTPGenerator;
import com.repos.UserRepo;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	   PasswordEncoder passwordEncoder;
//	@Autowired
	private EmailSender emailSender;
	public User findByEmail(String email) 
	{
		User user =  repo.findByEmails(email);
//		System.out.println(user);
		return user;
	}
	@Override
	public void add(UserCreateDto userDto) {
		User user = null;
		List<User> list = repo.findByEmailOrMobile(userDto.getEmail(),userDto.getMobile());
		System.out.println(list);
		if (!list.isEmpty()) {
			user = list.get(0);
		}
//		User user = userRepo.findByEmail(userDto.getEmail());
		if (user == null) {
//			String otp = OTPGenerator.generateOTP();
//			com.sachcoder.extras.EmailSender.sendOtp(userDto.getEmail(), otp);
//			userDto.setOtp(otp);
		 User user1 = new User();
			Role role =new Role();
			role.setName("USER");
			HashSet<Role> hashSet=new HashSet<>();
			hashSet.add(role);
			user1.setRole(hashSet);
			user1.setStatus("inactive");
			String otp = OTPGenerator.generateOTP();
//			EmailSender.sendOtp(userDto.getEmail(), otp);
			user1.setOtp(otp);
			user1.setEmail(userDto.getEmail());
			user1.setUsername(userDto.getUsername());
			user1.setMobile(userDto.getMobile());
		
			user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
//			User user2 = repo.save(ModelMapper.map(userDto, User.class));
//			return ModelMapper.map(user2, UserDto.class);
			 repo.save(user1);
			 
	 
	}
	 
	 
		
		
	}
	@Override
	public void authDeleteByUser(UserDeleteRequest userDeleteRequest) {
		 
		User user = repo.findByEmails(userDeleteRequest.getEmail());
		repo.deleteById(user.getId());
		System.out.println(user);
		
	}
	@Override
	public User getById(String id) {
		User user = repo.findByIds(id);
		return user;
	}
	@Override
	public User getByUsername(String email) {
//		System.out.println(email);
		User user =  repo.findByEmails(email);
//		System.out.println(user);
		return user;
	}
	 
	@Override
	public String verify(String email, String otp) {
//		System.out.println("yes111 ");
		System.out.println(email);
		System.out.println(otp);
		User user = repo.findByEmailAndOtp(email, otp);
//		System.out.println(user);
		if (user != null) {
			user.setStatus("active");
			repo.save(user);
			return "active";
		}
		return null;
	}
	@Override
	public UserVerifyDto resendOtp(String email) {
		 
//	 System.out.println("pppp");
		User user;
		Optional<User> optional = repo.findByEmail(email);
		if(optional.isPresent()) {
			user=optional.get();
//			System.out.println("hellohello");
			String otp = OTPGenerator.generateOTP();
			System.out.println(otp);
//			EmailSender.sendOtp(user.getEmail(), otp);
			user.setOtp(otp);
			User user2 = repo.save(user);
			return modelMapper.map(user2, UserVerifyDto.class);
//			return user2;
		}
		return null;
	}
	
	
	 
}
