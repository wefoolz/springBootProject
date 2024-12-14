package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class OrganizationExam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examId;
	
	@NotBlank
	@Size(min=3, max=20, message="Exam name length must be min 2 or max 20 charaters")
	private String examName;
	
	@NotBlank(message = "please provide date of the exam")
	private String date;
	
	private String description;
	private Recruiter recruiterId;
	private ExamJobseekar examJobseekar;
	
	@OneToMany(mappedBy = "exam")
	@JsonBackReference
	private List<OrganizationExamQuestions> questions;

}
