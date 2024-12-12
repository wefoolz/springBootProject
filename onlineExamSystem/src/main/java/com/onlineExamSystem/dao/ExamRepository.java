package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

}
