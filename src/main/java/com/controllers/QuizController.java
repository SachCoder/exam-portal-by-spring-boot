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

 import com.dto.QuizDto;
import com.service.QuizService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/addQuiz")
    public ResponseEntity<?> createQuiz(@Valid  @RequestBody QuizDto QuizDto ,BindingResult result) {
//    	System.out.println("hi");
    	if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
    	}
        QuizDto savedQuiz = quizService.saveQuiz(QuizDto);
        return ResponseEntity.ok(savedQuiz);
    }

    @PutMapping("/updateQuiz")
    public ResponseEntity<?> updateQuiz(@RequestBody QuizDto QuizDto ) {
        QuizDto updatedQuiz = quizService.updateQuiz(QuizDto);
//        System.out.println(updatedQuiz);
        return updatedQuiz != null ? ResponseEntity.ok(updatedQuiz) : ResponseEntity.badRequest().body("Invalid id or Quiz not found");
    }
     

    @DeleteMapping("/deleteQuiz/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable String id) {
//    	System.out.println("hiiiiiiiiiiii");
        if (quizService.getQuizById(id) != null) {
//        	System.out.println("hi");
            quizService.deleteQuiz(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    @GetMapping("/getByQuizId/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable String id) {
    	
        QuizDto Quiz = quizService.getQuizById(id);
        return Quiz != null ? ResponseEntity.ok(Quiz) : ResponseEntity.badRequest().body("Invalid id");
    }
    
    @GetMapping("/active/{id}")
    public ResponseEntity<?> getQuizByStatus(@PathVariable String id) {
    	
        List<QuizDto> Quiz = quizService.getQuizByStatus(id);
        return Quiz != null ? ResponseEntity.ok(Quiz) : ResponseEntity.badRequest().body("Invalid id");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuizs() {
         List<QuizDto> Quizs = quizService.getAllQuizs();
        
        return ResponseEntity.ok(Quizs);
    }
    
}
