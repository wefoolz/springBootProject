package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

}
