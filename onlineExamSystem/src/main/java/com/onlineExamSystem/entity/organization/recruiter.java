package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onlineExamSystem.entity.institute.ClassRoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class recruiter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recruiterId;
	private String recruiterNameL;
	private String recruiterNamef;
	@NotBlank
	@Size(min=3, max=20, message="name length must be min 2 or max 20 charaters")
	private String userName;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
			message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces."
	)
	private String password;

	@NotBlank
	private String emailId;
	private OrganizationAdmin organizationId;

	private OrganizationExam exam;

}
