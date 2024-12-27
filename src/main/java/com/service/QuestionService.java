package com.service;

import com.dto.QuestionDto;
import com.entities.Question;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionService {
//    QuestionDto saveQuestion(QuestionDto Question);
    QuestionDto updateQuestion(QuestionDto Question);
    void deleteQuestion(String id);
//    QuestionDto getQuestionById(String id);
    List<QuestionDto> getAllQuestions();
 	QuestionDto saveQuestion(@Valid QuestionDto question);
	List<QuestionDto> getQuestionByQuizId(String id);
	QuestionDto getQuestionById(String id);
	Map<String, Object>  get( @Valid List<QuestionDto> questions);
}
