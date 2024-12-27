package com.service;

import java.util.List;

import com.dto.QuizDto;

import jakarta.validation.Valid;

public interface QuizService {

  

 
    void deleteQuiz(String QuizId);

//    QuizDto getQuizById(int id);


	QuizDto saveQuiz(@Valid QuizDto QuizDto);

	List<QuizDto> getAllQuizs();


 
//	QuizDto getQuizById(int id);

	QuizDto getQuizById(String QuizId);

 
	QuizDto updateQuiz(QuizDto quizDto);

	List<QuizDto> getQuizByStatus(String id);

//	EmployeeDto getQuizByEmployeeId(QuizDto QuizDto, int emp_id);

	 

 }
