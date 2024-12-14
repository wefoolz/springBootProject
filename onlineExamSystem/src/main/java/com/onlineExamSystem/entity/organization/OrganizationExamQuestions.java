package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class OrganizationExamQuestions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@NotBlank(message="please enter Question")
	private String questions;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private OrganizationExam examId;
	
	@OneToMany(mappedBy = "questions")
	@JsonBackReference
	private List<OrganizationExamQuestionsOptions> options;

	
}