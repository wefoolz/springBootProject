package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.institute.InstituteExamQuestionsOptions;

public interface OptionsRepository extends JpaRepository<InstituteExamQuestionsOptions, Integer> {

}
