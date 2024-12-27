package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {
	
	private String id;
	
    @NotBlank(message = "title Name cannot be blank")
    @Size(max = 100, message = "title Name cannot be more than 100 characters")
    private String title;
    
    private String description;
    
    private String maxMarks;
    
    private int NoOfQuestions;
    
    private Boolean active;
//@JsonIgnore
	private CategoryDto category;
//	@OneToMany(mappedBy = "department")
//	private List<AttendenceRecord> attendence_record;
@JsonIgnore
	private UserCreateDto user;
	 
}
