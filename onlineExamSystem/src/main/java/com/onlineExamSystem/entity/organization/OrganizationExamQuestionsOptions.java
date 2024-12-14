package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class OrganizationExamQuestionsOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opoitnId;
	
	@NotBlank(message="Enter Option Text")
	private String options;
	
	@NotBlank(message="The answer is correct or not")
	private boolean isCorrect;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private OrganizationExamQuestions questionsId;

}
