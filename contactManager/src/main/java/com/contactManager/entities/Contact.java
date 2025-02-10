package com.contactManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	
	@NotBlank(message="Please Enter a Name for the contact!!")
	private String name;
	private String secondName;
	private String work;
	@Pattern(regexp= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email!!")
	private String email;
	
	@NotBlank
	@Size(min=10, max=10, message="length of phone no. must be 10 digits")
	private String phone;
	private String image;
	
	@Column(length=10000)
	@Size(max=5000, message="The Data is to big")
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public Contact(int cid, String name, String secondName, String work, String email, String phone, String image,
			String description, User user) {
		super();
		this.cid = cid;
		this.name = name;
		this.secondName = secondName;
		this.work = work;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.description = description;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact() {
	}


	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//
//	@Override
//	public String toString() {
//		return "Contact [cid=" + cid + ", name=" + name + ", secondName=" + secondName + ", work=" + work + ", email="
//				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + ", User="+user+"]";
//	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	

	@Override
	public boolean equals(Object obj) {
		return this.cid==((Contact)obj).getCid();
	}
}
