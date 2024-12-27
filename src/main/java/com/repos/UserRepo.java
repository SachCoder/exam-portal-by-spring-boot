package com.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, String>{
	@Query("select u from User u where u.email = :email")
 User findByEmails(@Param("email") String email);

	Optional<User> findByEmail(String username);
	
	@Query("select u from User u where u.id = :id")
	User findByIds(@Param("id") String id);
	@Query("select u from User u where u.email = :email and u.mobile= :mobile" )
	public List<User> findByEmailOrMobile(@Param("email") String email, @Param("mobile") String mobile);
//	@Query("select u from User u where u.email = :email and u.otp= :otp" )
	User findByEmailAndOtp(@Param("email") String email,@Param("otp") String otp);
  
}
