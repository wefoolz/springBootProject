package com.onlineExamSystem.entity.institute;

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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    
    @NotBlank
	@Size(min=3,message="First Name must contain minimum 3 characters")
    private String studentNameL;
    
    @NotBlank
	@Size(min=3,message="Last Name must contain minimum 3 characters")
    private String studentNameF;
    
    @NotBlank
	@Size(min=4, max=20, message="UserName must contain min 4 and max 20 charaters")
    private String userName;
    
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
			message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces."
	)
	@NotBlank
    private String password;
    
    @NotBlank
	@Pattern(regexp= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email!!")
    private String emailId;
    
    //private InstituteStudent instituteStudent;
    
    @OneToMany(mappedBy = "teacherId")
    @JsonBackReference
	private List<ClassRoomStudent> classRoomStudent;
}
