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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "teacherexam")
public class TeacherExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examid;

	@NotBlank
	@Size(min = 3, message = "Exam name length must be min 3 charaters")
	private String examname;

	@NotBlank(message = "please provide date of the exam")
	private String date;

	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ClassRoom classroomid;

	private int teacherid;

	@OneToMany(mappedBy = "examid")
	@JsonBackReference
	private List<TeacherExamQuestions> questions;

	@OneToMany(mappedBy = "examid")
	@JsonBackReference
	private List<TeacherExamResults> results;

}
