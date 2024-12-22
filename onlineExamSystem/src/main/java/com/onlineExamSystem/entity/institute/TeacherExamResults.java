package com.onlineExamSystem.entity.institute;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TeacherExamResults{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	
	private String result;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExam examId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ClassRoomStudent classRoomStudentId;

}
