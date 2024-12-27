package com.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private boolean blocked=false;
	private String username;
private String mobile;
	private String phone;
private String Status;
	private String email;
private String otp;
@JsonIgnore
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name ="user_role", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
 	private Set<Role> role = new HashSet<>();
 
	
	@OneToMany(mappedBy = "user" )
	@JsonIgnore
	@JsonManagedReference
	private List<Answer>  answer;
	
	@OneToMany(mappedBy = "user" )
	@JsonIgnore
	@JsonManagedReference
	private List<Quiz>  quiz;
	 
	@OneToMany(mappedBy = "user" )
	@JsonIgnore
	@JsonManagedReference
	private List<Question>  question;

	@Override
	public String toString() {
		return "User [id=" + id + ", blocked=" + blocked + ", username=" + username + ", mobile="  + ", Status="
				+ Status + ", email=" + email + ", otp="  + ", password=" + password + ", role=" + role
				+ ", employee=" +  ", department= ]";
	}
	
	

}
