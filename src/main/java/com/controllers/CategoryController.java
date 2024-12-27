package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CategoryDto;
import com.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> createshift(@Valid  @RequestBody CategoryDto categoryDto ,BindingResult result) {
//    	System.out.println("hi");
    	if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
    	}
        CategoryDto savedshift = categoryService.saveCategory(categoryDto);
        return ResponseEntity.ok(savedshift);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateshift(@RequestBody CategoryDto categoryDto, @PathVariable String id) {
        categoryDto.setCid(id);
        CategoryDto updatedshift = categoryService.updateCategory(categoryDto);
//        System.out.println(updatedshift);
        return updatedshift != null ? ResponseEntity.ok(updatedshift) : ResponseEntity.badRequest().body("Invalid id or shift not found");
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteshift(@PathVariable String id) {
//    	System.out.println("hiiiiiiiiiiii");
        if (categoryService.getCategoryById(id) != null) {
//        	System.out.println("hi");
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<?> getshiftById(@PathVariable String id) {
    	
        CategoryDto shift = categoryService.getCategoryById(id);
        return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.badRequest().body("Invalid id");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllshifts() {
        List<CategoryDto> shifts = categoryService.getAllCategory();
        return ResponseEntity.ok(shifts);
    }
}

