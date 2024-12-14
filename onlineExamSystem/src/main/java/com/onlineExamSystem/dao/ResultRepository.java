package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.institute.InstituteExamResults;

public interface ResultRepository extends JpaRepository<InstituteExamResults, Integer> {

}
