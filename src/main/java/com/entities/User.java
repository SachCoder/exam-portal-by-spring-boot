package com.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
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
//	private String phone;
private String Status;
	private String email;
private String otp;
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name ="user_role", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
//	@Column(nullable = false)
	private Set<Role> role = new HashSet<>();
	
//	@Transient
	@OneToMany(mappedBy = "user")
	private List<Employee>  employee;
	
 

}
