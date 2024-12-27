package com.controllers;

import java.util.ArrayList;
import java.util.Collections;
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

import com.dto.QuestionDto;
import com.dto.QuizDto;
import com.service.QuestionService;
import com.service.QuizService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("question")
public class QuestionController {

	private final QuestionService questionService; 
	private final QuizService quizService;
	 

	public QuestionController(QuestionService QuestionService , QuizService quizService ) {
		this.questionService = QuestionService;
		this.quizService = quizService;
		 
	}

    @PostMapping("/add")
	public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDto question,BindingResult result ) {
//		System.out.println("hi");
		if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
			
		}
 		  
		QuestionDto savedQuestion = questionService.saveQuestion(question );
//		System.out.println(savedQuestion);
		if (savedQuestion != null)
			return ResponseEntity.ok(savedQuestion);
		else
			return ResponseEntity.badRequest().body("Email or Phone already registered");
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody QuestionDto Question ,BindingResult result) {
 		if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
			 
		}
 
			QuestionDto updatedQuestion = questionService.updateQuestion(Question);
			if (updatedQuestion == null) {
				return ResponseEntity.badRequest().body("Update not succeed");
			} else
				return ResponseEntity.ok(updatedQuestion);
		} 
 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String id) {
 			questionService.deleteQuestion(id);
//			System.out.println("okkk");
			return ResponseEntity.ok().build();
		}
 
 

	@GetMapping("/getByQuizId/{id}")
	public ResponseEntity<?> getQuestionByQuizId(@PathVariable String id) {
 		List<QuestionDto> question = questionService.getQuestionByQuizId(id);
 		QuizDto quiz = quizService.getQuizById(id);
 		List list =new ArrayList(question);
 		
 		if(list.size()>  quiz.getNoOfQuestions()) {
 			list = list.subList(0, quiz.getNoOfQuestions()+1);
 		}
 		Collections.shuffle(list);
 		
 		return list != null ? ResponseEntity.ok(list) : ResponseEntity.badRequest().body("Invalid id");
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getQuestionById(@PathVariable String id) {
 		QuestionDto question = questionService.getQuestionById(id);
 		return question != null ? ResponseEntity.ok(question) : ResponseEntity.badRequest().body("Invalid id");
	}
	 
 
	@GetMapping("/all")
	public ResponseEntity<?> getAllQuestions() {
//		System.out.println("you click here");
		List<QuestionDto> Questions = questionService.getAllQuestions();
//		System.out.println(Questions);
		if(Questions!=null) {
			return ResponseEntity.ok(Questions);
		}
		return  null;
	}
	
	//for eval-quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@Valid @RequestBody List<QuestionDto> questions,BindingResult result){
		if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
			
		}
							
			Map<String, Object> map = questionService.get(questions);
			return ResponseEntity.ok(map);
	 	
	}
	
}
