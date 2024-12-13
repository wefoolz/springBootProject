package com.onlineExamSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	
	private int result;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Exam examId;
	private ClassRoomStudent classRoomStudentId;

}
