package com.dto;

 

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private String cid;

    @NotBlank(message = "Category title cannot be blank")
    @Size(max = 100, message = "category title cannot be more than 100 characters")
    private String title;

    @NotNull(message = "Start Time cannot be null")
     private String description;
 
    
     
    private  UserCreateDto  user;
    
    public void setTotalShiftHour() {
      
    }

   
}
