package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class RecruiterExamQuestionsOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opoitnId;
	
	@NotBlank(message="Enter Option Text")
	private String options;
	
	private boolean isCorrect;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private RecruiterExamQuestions questionsId;

}
