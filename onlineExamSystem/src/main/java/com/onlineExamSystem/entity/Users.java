package com.onlineExamSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onlineExamSystem.entity.institute.ClassRoom;
import com.onlineExamSystem.entity.institute.ClassRoomStudent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@NotBlank
	@Size(min = 3, message = "First Name must contain minimum 3 characters")
	private String fname;

	@NotBlank
	@Size(min = 3, message = "Last Name must contain minimum 3 characters")
	private String lname;

	@Size(min = 10, max=10,message = "Mobile number must be 10 digits")
	@Pattern(regexp = "^$|[0-9]{10}")
	@NotNull
	private String mobile;
	
	@NotBlank
	@Size(min = 4, max = 20, message = "UserName must contain min 4 and max 20 charaters")
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!!")
	private String emailid;
	
	@NotBlank
	private String role;

	// private InstituteStudent instituteStudent;

	@OneToMany(mappedBy = "teacherid")
    @JsonBackReference
	private List<ClassRoom> classroom;
	
	@OneToMany(mappedBy = "studentid")
	@JsonBackReference
	private List<ClassRoomStudent> classroomstudent;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ClassRoom> getClassRoom() {
		return classroom;
	}

	public void setClassRoom(List<ClassRoom> classroom) {
		this.classroom = classroom;
	}

	public List<ClassRoomStudent> getClassroomstudent() {
		return classroomstudent;
	}

	public void setClassRoomStudent(List<ClassRoomStudent> classroomstudent) {
		this.classroomstudent = classroomstudent;
	}
	
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	protected Users(int userid,
			@NotBlank @Size(min = 3, message = "First Name must contain minimum 3 characters") String fname,
			@NotBlank @Size(min = 3, message = "Last Name must contain minimum 3 characters") String lname, String mobile,
			@NotBlank @Size(min = 4, max = 20, message = "UserName must contain min 4 and max 20 charaters") String username,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces.") @NotBlank String password,
			@NotBlank @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!!") String emailid,
			@NotBlank String role) {
		super();
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.emailid = emailid;
		this.role = role;
	}

	protected Users(int userid,
			@NotBlank @Size(min = 3, message = "First Name must contain minimum 3 characters") String fname,
			@NotBlank @Size(min = 3, message = "Last Name must contain minimum 3 characters") String lname, String mobile,
			@NotBlank @Size(min = 4, max = 20, message = "UserName must contain min 4 and max 20 charaters") String username,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces.") @NotBlank String password,
			@NotBlank @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!!") String emailid,
			@NotBlank String role, List<ClassRoom> classroom, List<ClassRoomStudent> classroomstudent) {
		super();
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.emailid = emailid;
		this.role = role;
		this.classroom = classroom;
		this.classroomstudent = classroomstudent;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", fname=" + fname + ", lname=" + lname +", mobile="+mobile+ ", username=" + username
				+ ", password=" + password + ", emailid=" + emailid + ", role=" + role + "]";
	}

	

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
