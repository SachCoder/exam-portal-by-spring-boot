package com;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.entities.Role;
import com.entities.User;
import com.repos.UserRepo;

@SpringBootApplication
public class BiometricApplication implements CommandLineRunner{
//	@Autowired
//	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	 public void createUsers() {
//		 
//	        User user = new User();
////	        Long id = new Long(random.nextInt(100));
////	        user.setUserId(id);
//	        user.setEmail("user3@gmail.com");
//	        user.setPassword(passwordEncoder.encode("user"));
//	        HashSet<Role> hashSet= new HashSet<>();
//	        Role role = new Role();
//	        role.setName("ADMIN");
//	        hashSet.add(role);
//	        user.setStatus("active");
//	        
//	        user.setRole(hashSet);
//	        user.setUsername("user");
//	        User save = this.repo.save(user);
//
//	        System.out.println(save);
//
//	    }
//	
	public static void main(String[] args) {
		SpringApplication.run(BiometricApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		createUsers();
	}
	
	

}
