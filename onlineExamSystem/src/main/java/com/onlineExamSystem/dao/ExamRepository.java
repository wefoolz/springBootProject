package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.institute.InstituteExam;

public interface ExamRepository extends JpaRepository<InstituteExam, Integer> {

}
