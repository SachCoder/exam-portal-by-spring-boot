package com.dto;

  
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
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
