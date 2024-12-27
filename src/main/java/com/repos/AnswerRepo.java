package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, String> {
 
 
}