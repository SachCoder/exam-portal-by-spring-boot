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
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String title;
	private String description;
	private int maxMarks;
	private int NoOfQuestions;
	private Boolean active;
	
	 
	
	@OneToMany(mappedBy = "quiz")
	@JsonManagedReference
	private List<Question>  question;
	
	@OneToMany(mappedBy = "quiz")
	@JsonManagedReference
	private List<Answer>  answer;
	
	
	@ManyToOne
	 @JsonBackReference
   @JoinColumn(name = "category_cid")
   private Category category;
	
	
	@ManyToOne
	 @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
	
}
