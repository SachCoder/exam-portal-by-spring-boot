 package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dto.CategoryDto;
import com.dto.UserCreateDto;
import com.entities.Category;
import com.entities.User;
import com.repos.CategoryRepo;
import com.repos.UserRepo;
import com.security.CustomUserDetails;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepo CategoryRepo;
	private final UserRepo userRepo;
	 
    private final ModelMapper modelMapper;

     public CategoryServiceImpl(CategoryRepo CategoryRepo, ModelMapper modelMapper,UserRepo userRepo ) {
        this.CategoryRepo =CategoryRepo;
		this.userRepo = userRepo;
		 
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto CategoryDto) {
//    	System.out.println("hi");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails obj =(CustomUserDetails) auth.getPrincipal();
		  String id = obj.getId();
    			Optional<User>  user = userRepo.findById(id);
    	if(user.isPresent()) {
    		User u1 = user.get();
//    				System.out.println(u1);
    		 
  		  Optional<Category> depart =  CategoryRepo.findByCategoryNames(CategoryDto.getTitle(), id );
 	 
if(depart.isEmpty()) {
	UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
//	System.out.println(userDto);
	 
	 CategoryDto.setUser(userDto);
	  
	 
	  
//	 CategoryDto.setTotalCategoryHour(CategoryDto.getStartTime()-CategoryDto.getEndTime());
Category Category = modelMapper.map(CategoryDto, Category.class);
//System.out.println(Category);
Category savedCategory = CategoryRepo.save(Category);
//System.out.println(savedCategory);
return modelMapper.map(savedCategory, CategoryDto.class);

}
}
 
    			return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto CategoryDto) {
        if (!CategoryRepo.existsById(CategoryDto.getCid())) {
            return null; // Or throw an exception
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails obj =(CustomUserDetails) auth.getPrincipal();
		  String userId = obj.getId();
//		  Category depart =  CategoryRepo.findByDepart_Id(CategoryDto.getDepart_Id(), userId);
// if(depart==null) {
		Optional<User>  user = userRepo.findById(userId);
		if(user.isPresent()) {
			User u1 = user.get();
//			System.out.println("yes");
			UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
//			System.out.println(userDto);
			CategoryDto.setUser(userDto);
//			 CategoryDto.setTotalCategoryHour();
    Category Category = modelMapper.map(CategoryDto, Category.class);
    Category updatedCategory = CategoryRepo.save(Category);
    return modelMapper.map(updatedCategory, CategoryDto.class);
 
		
	       
			 
    }
//	        }
	        return null;
    }

    @Override
    @Transactional
    public void deleteCategory(String CategoryId) {
     
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated()) {
			 CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
		        String userId = obj.getId();
			 Optional<Category> Category = CategoryRepo.findByCategory_Id(CategoryId,userId);
			 if(!Category.isPresent()) {
				 System.out.println("not deleted");
			 }
			if(Category.isPresent()) {
			 Category s1 = Category.get();
//			 s1.getEmployee().forEach(employee->employee.setCategory(null));
//			 employeeRepo.saveAll(s1.getEmployee());
			 CategoryRepo.delete(s1);
		 }
    }
    }
     
     
    	 
		 

    @Override
    public CategoryDto getCategoryById(String CategoryId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated()) {
			 Optional<Category> Category = CategoryRepo.findById(CategoryId);
		        return Category.map(d -> modelMapper.map(d, CategoryDto.class)).orElse(null);
		 }
        return  null;
    }

 
    @Override
    public List<CategoryDto> getAllCategory() {
        // Get the authenticated user's ID
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  if(auth.isAuthenticated()){
     List<Category> category = CategoryRepo.findAll();
 
    return category.stream()
            .map(Category -> modelMapper.map(Category, CategoryDto.class))
            .collect(Collectors.toList());
}

       
 return null;
}
	 

}