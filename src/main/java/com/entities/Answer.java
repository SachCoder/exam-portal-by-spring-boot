package com.entities;

import java.time.LocalDateTime;
 
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
   
   private Boolean correctAnswer = false;
   private Boolean attempted=false;
   private String givenAnswer;
   
   private LocalDateTime fsubmit;
  
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "quiz_id" )
   private Quiz quiz;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "question_id" )
   private Question question;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id" )
   private User user;
    
    
    
	
  
}

