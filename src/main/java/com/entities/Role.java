package com.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@Override
	public String toString() {
		return this.name;
	}
	
//	@OneToOne
////	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private User user;
	
	
}