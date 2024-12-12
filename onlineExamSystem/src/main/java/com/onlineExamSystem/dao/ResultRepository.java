package com.onlineExamSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamSystem.entity.Results;

public interface ResultRepository extends JpaRepository<Results, Integer> {

}
