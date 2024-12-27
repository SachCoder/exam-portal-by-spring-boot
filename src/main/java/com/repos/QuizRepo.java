package com.repos;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entities.Question;
import com.entities.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, String> {

 
	@Query("select u from Quiz u where u.user.id =:id")
	public List<Quiz> findByUser_Id(@Param("id")  String id);
	@Query("SELECT u FROM Quiz u WHERE u.id = :depart_id AND u.user.id = :id  ")
	Optional<Quiz> findById(@Param("depart_id") String depart_id, @Param("id") String id );
	@Query("SELECT u FROM Quiz u WHERE u.title = :name AND u.user.id = :id  ")
	Optional<Quiz> findByTitle(@Param("name") String title, @Param("id") String id );
	public boolean existsByTitle(String title);
	@Query("SELECT u FROM Quiz u WHERE u.id = :id AND u.title= :title")
	public Quiz findByIdAndTitle(@Param("id") String id,@Param("title") String title);
	@Query("SELECT u FROM Quiz u WHERE u.active = :active AND u.category.cid =:id")
	public List<Quiz> findByStatus(@Param("active") Boolean active,@Param("id") String id);
//	public Quiz get(Quiz quiz);
	 
	 
}
