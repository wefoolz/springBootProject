package com.onlineExamSystem.entity.institute;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TeacherExamQuestionsOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opoitnId;
	
	@NotBlank(message="Enter Option Text")
	private String options;
	
	private boolean isCorrect;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExamQuestions questionsId;

}
