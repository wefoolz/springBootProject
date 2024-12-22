package com.onlineExamSystem.entity.institute;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TeacherExamQuestions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@NotBlank(message="please enter Question")
	private String questions;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExam examId;
	
	@OneToMany(mappedBy = "questions")
	@JsonBackReference
	private List<TeacherExamQuestionsOptions> options;

	
}
