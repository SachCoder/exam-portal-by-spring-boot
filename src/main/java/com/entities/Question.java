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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String content;

   
   private String option1;
  
   private String option2;
  
   private String option3;
  
   private String option4;
//  @JsonIgnore
   private String answer;
   
   private String givenAnswer; 
   
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "quiz_id" )
   private Quiz quiz;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id" )
   private User user;
    
    @OneToMany(mappedBy = "question")
	@JsonManagedReference
	private List<Answer>  answerIs;
    
    
    
    
    
    
	
  
}

