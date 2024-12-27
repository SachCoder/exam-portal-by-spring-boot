package com.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {
//	@Query("select u from Category u where u.user.id =:id")
	public List<Category> findByUser_Id(  String id);
	@Query("SELECT u FROM Category u WHERE u.id = :Category_id AND u.user.id = :id  ")
	Optional<Category> findByCategory_Id(@Param("Category_id") String Category_id, @Param("id") String id );
	@Query("SELECT u FROM Category u WHERE u.title = :name AND u.user.id = :id  ")
	Optional<Category> findByCategoryNames(@Param("name") String title, @Param("id") String id );
	 
	public boolean existsByTitle(String CategoryTitle);
 
}