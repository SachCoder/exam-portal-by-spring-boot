package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.repos.UserRepo;
import com.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepo repo;
  @Autowired
  private UserService userService;

 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
//        System.out.println(user);
        return CustomUserDetails.create(user);
    }
 
  public UserDetails loadUserById(String id) {
      User user = userService.getById(id);
      return CustomUserDetails.create(user);
  }
}
