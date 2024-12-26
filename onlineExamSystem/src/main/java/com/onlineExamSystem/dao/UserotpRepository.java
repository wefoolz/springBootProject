package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineExamSystem.entity.Userotp;

import jakarta.transaction.Transactional;

public interface UserotpRepository extends JpaRepository<Userotp, Integer>{
	
	
	@Query("select u from Userotp u where u.username = :username")
	public Userotp getUserByUserName(@Param("username") String username);
	
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Userotp u WHERE u.username = :username")
	void deleteUserByUserName(@Param("username") String username);
}
