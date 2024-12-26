package com.onlineExamSystem.entity.institute;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teacherexamquestionsoptions")
public class TeacherExamQuestionsOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opoitnid;
	
	@NotBlank(message="Enter Option Text")
	private String options;
	
	private boolean iscorrect;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExamQuestions questionsid;

}
