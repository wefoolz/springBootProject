package com.onlineExamSystem.entity.organization;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Recruiter {
	
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
	private OrganizationRecruiter organizationRecruiter;

	private OrganizationExam exam;

}
