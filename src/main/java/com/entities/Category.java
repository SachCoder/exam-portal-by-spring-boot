package com.entities;

 import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
  import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String cid;

	private String title;
 	private String description;
  
	 
	
 	@OneToMany(mappedBy = "category" )
//	@JsonIgnore
	@JsonManagedReference
	private List<Quiz>  quiz;
 
	@ManyToOne
	 @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
//	

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", title=" + title + ", description=" + description + ", quiz=" + quiz
				+ ", user=" + user + "]";
	}
	
}
