package com.dto;

 import java.time.LocalDateTime;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

  @Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
	private  String id;
 
     
    private Boolean correctAnswer = false;
    private Boolean attempted = false;
    private String givenAnswer;
    private LocalDateTime fsubmit;
   
  private QuestionDto question;
	 private QuizDto quiz;
	 private UserCreateDto user;

	 
    
         
    
  
}
