package com.dto;

  import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
	private  String id;

    @NotNull(message = "Question name cannot be null")
    @Size(min = 2, max = 100, message = "Question name must be between 2 and 100 characters")
    private String content;

//    private DepartmentDto department;
    
    @NotNull(message = "Question name cannot be null")
    private String option1;
    
    @NotNull(message = "Question name cannot be null")
    private String option2;
    private String qid;
    private String qtitle;
    
    @NotNull(message = "Question name cannot be null")
    private String option3;
    
    @NotNull(message = "Question name cannot be null")
    private String option4;
   
    private String answer;
     
    
    @JsonIgnore
    public String getAnswer() {
		return answer;
	}
    @JsonProperty
	public void setAnswer(String answer) {
		this.answer = answer;
	}
//	@Transient
    private String givenAnswer;
	 private QuizDto quiz;
	 private UserCreateDto user;
    
         
    
  
}
