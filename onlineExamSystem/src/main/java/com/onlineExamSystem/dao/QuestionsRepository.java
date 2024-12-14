package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.institute.InstituteExamQuestions;

public interface QuestionsRepository extends JpaRepository<InstituteExamQuestions, Integer> {

}
