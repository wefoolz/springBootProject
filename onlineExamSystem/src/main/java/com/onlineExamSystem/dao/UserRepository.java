package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.institute.Teacher;

public interface UserRepository extends JpaRepository<Teacher, Integer>{

}
