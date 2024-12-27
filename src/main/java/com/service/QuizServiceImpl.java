package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

 import com.dto.QuizDto;
import com.dto.UserCreateDto;
 import com.entities.Quiz;
import com.entities.User;
import com.repos.QuizRepo;
import com.repos.UserRepo;
import com.security.CustomUserDetails;

import jakarta.transaction.Transactional;

@Service
public class QuizServiceImpl implements QuizService {

	private final QuizRepo QuizRepo;
	private final UserRepo userRepo;
     private final ModelMapper modelMapper;

 
    public QuizServiceImpl(QuizRepo QuizRepo, ModelMapper modelMapper,UserRepo userRepo ) {
        this.QuizRepo = QuizRepo;
		this.userRepo = userRepo;
         this.modelMapper = modelMapper;
    }

    @Override
    public QuizDto saveQuiz(QuizDto quizDto) {
//    	System.out.println("hi");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails obj =(CustomUserDetails) auth.getPrincipal();
		  String id = obj.getId();
    			Optional<User>  user = userRepo.findById(id);
    	if(user.isPresent()) {
    		User u1 = user.get();
//    				System.out.println(u1);
    		 
  		  Optional<Quiz> depart =  QuizRepo.findByTitle(quizDto.getTitle(), id );
 	 
if(depart.isEmpty()) {
	UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
//	System.out.println(userDto);
	 quizDto.setUser(userDto);
	quizDto.setCategory(quizDto.getCategory());
Quiz Quiz = modelMapper.map(quizDto, Quiz.class);
//System.out.println(Quiz);
Quiz savedQuiz = QuizRepo.save(Quiz);
//System.out.println(savedQuiz);
return modelMapper.map(savedQuiz, QuizDto.class);

}
}
 
    			return null;
    }

 
@Override
     public QuizDto updateQuiz(QuizDto quiz) {
  	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	        if(auth.isAuthenticated()) {
	        	CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
	        String userId = obj.getId();	
	        
	      Optional<User>  user = userRepo.findById(userId);
			if(user.isPresent()) {
				User u1 = user.get();
				UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
			Optional<Quiz>  q1 = QuizRepo.findById(quiz.getId());
			if(q1.isPresent() ) {
 				Quiz e1 = q1.get();
 				quiz.setId(e1.getId() );  
 				quiz.setUser(userDto);
 				quiz.setCategory(quiz.getCategory());
				Quiz updatedQuestion = modelMapper.map(quiz, Quiz.class);
      Quiz savedQuestion = QuizRepo.save(updatedQuestion);
      return modelMapper.map(savedQuestion, QuizDto.class);
			}
	        }
	        }
			return null;
  }

	 
    
    @Override
    @Transactional
    public void deleteQuiz(String QuizId) {
     
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated()) {
			 CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
		        String userId = obj.getId();
			 Optional<Quiz> Quiz = QuizRepo.findById(QuizId,userId);
			 if(!Quiz.isPresent()) {
//				 System.out.println("not deleted");
				 
				 
			 }
			 else {
			 Quiz d1 = Quiz.get();
 
			 QuizRepo.delete(d1);
			 }
		 
		 }
    }

    @Override
    public QuizDto getQuizById(String QuizId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated()) {
			 Optional<Quiz> Quiz = QuizRepo.findById(QuizId);
		        return Quiz.map(d -> modelMapper.map(d, QuizDto.class)).orElse(null);
		 }
        return  null;
    }
    
    @Override
    public List<QuizDto> getQuizByStatus(String id) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated()) {
			 Boolean active = true;
			 List<Quiz> quiz = QuizRepo.findByStatus(active,id);
			 
			  return quiz.stream()
			            .map(quizs -> modelMapper.map(quizs, QuizDto.class))
			            .collect(Collectors.toList());
			 }

			       
			 return null;
			}

 
    @Override
    public List<QuizDto> getAllQuizs() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
        String userId = obj.getId();
 if(auth.isAuthenticated()){
     List<Quiz> quizs = QuizRepo.findByUser_Id(userId);
    return quizs.stream()
            .map(Quiz -> modelMapper.map(Quiz, QuizDto.class))
            .collect(Collectors.toList());
 }

       
 return null;
}

	 
}
 