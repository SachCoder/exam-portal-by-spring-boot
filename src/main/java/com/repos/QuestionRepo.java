package com.repos;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dto.QuestionDto;
import com.entities.Question;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionRepo extends JpaRepository<Question, String> {
//List<Question> findByPhoneOrEmail(String phone, String email);
//@Query("select u form Question where u.emp_id = :emp_id")
 
 
//public List<Question> findByUser_Id(String id);
 

@Transactional
@Modifying
@Query("DELETE FROM Question WHERE  id = :id ")
public  void deleteById(@Param("id") int id );
@Query("SELECT u FROM Question u WHERE u.quiz.id = :id")
List<Question> findByQuizId(@Param("id") String id);
@Query("SELECT u FROM Question u WHERE u.id = :id")
Question findByIds(@Param("id") String id);
 
}
