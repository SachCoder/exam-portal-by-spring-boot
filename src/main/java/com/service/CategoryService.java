package com.service;

import java.util.List;

import com.dto.CategoryDto;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto Category);
    CategoryDto updateCategory(CategoryDto Category);
    void deleteCategory(String CategoryId);
    CategoryDto getCategoryById(String CategoryId);
    List<CategoryDto> getAllCategory();
    }
 
 
 
 



 