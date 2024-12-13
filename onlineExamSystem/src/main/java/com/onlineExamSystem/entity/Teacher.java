package com.onlineExamSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherId;
	private String teacherNameL;
	private String teacherNamef;
	@NotBlank
	@Size(min=3, max=20, message="name length must be min 2 or max 20 charaters")
	private String userName;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
			message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces."
	)
	private String password;

	@NotBlank
	private String emailId;
	private InstituteAdmin instituteId;

	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<ClassRoom> results;
	private Exam exam;

}
