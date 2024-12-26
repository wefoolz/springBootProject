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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teacherexamquestions")
public class TeacherExamQuestions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionid;
	
	@NotBlank(message="please enter Question")
	private String questions;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExam examid;
	
	@OneToMany(mappedBy = "questionsid")
	@JsonBackReference
	private List<TeacherExamQuestionsOptions> options;

	
}
