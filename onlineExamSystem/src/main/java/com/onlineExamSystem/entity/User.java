package com.onlineExamSystem.entity;

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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank
	@Size(min=3, max=20, message="name length must be min 2 or max 20 charaters")
	private String userName;
	
	 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces."
		    )
	private String password;
	 
	 @NotBlank
	private String mail;
	 
	private String role;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private Results results;
	
	protected User() {
		
	}

	
	
	protected User(int userId,
			@NotBlank @Size(min = 3, max = 20, message = "name length must be min 2 or max 20 charaters") String userName,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must be 8-20 characters long, include at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces.") String password,
			@NotBlank String mail, String role, Results results) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.role = role;
		this.results = results;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", mail=" + mail
				+ ", role=" + role + ", results=" + results + "]";
	}
	
	
	
	
	
	
	

}
