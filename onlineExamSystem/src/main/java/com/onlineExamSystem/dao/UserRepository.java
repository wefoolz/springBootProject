package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineExamSystem.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("select u from Users u where u.username = :username")
	public Users getUserByUserName(@Param("username") String username);

	@Query("select u from Users u where u.emailid = :email")
	public Users getUserByEmail(@Param("email") String email);

	
}
