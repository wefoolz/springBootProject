package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}