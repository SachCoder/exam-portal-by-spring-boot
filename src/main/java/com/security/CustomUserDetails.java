package com.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entities.Role;
import com.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    public String id;
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String id, String userName, String password, String eMail, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.email = eMail;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(User user) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        grantedAuthorityList.add(new SimpleGrantedAuthority("user"));
        Set<Role> roles=user.getRole();
		for(Role role:roles) {
			grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//			System.out.println(role.getName());
		}
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), grantedAuthorityList);
    }

    @Override
    public String getUsername() {
        return email;
    }
    
    public String getId() {
		return id;
    	
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
