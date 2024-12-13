package com.onlineExamSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examId;
	
	@NotBlank
	@Size(min=3, max=20, message="Exam name length must be min 2 or max 20 charaters")
	private String examName;
	
	@NotBlank(message = "please provide date of the exam")
	private String date;
	
	private String description;

	private ClassRoom classRoomId;
	private Teacher teacherId;
	@OneToMany(mappedBy = "exam")
	@JsonBackReference
	private List<Results> results;
	
	@OneToMany(mappedBy = "exam")
	@JsonBackReference
	private List<Questions> questions;

}
