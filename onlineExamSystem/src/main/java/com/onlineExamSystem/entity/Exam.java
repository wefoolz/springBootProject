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
	
	@OneToMany(mappedBy = "exam")
	@JsonBackReference
	private List<Results> results;
	
	@OneToMany(mappedBy = "exam")
	@JsonBackReference
	private List<Questions> questions;

	protected Exam() {
		
	}

	protected Exam(int examId,
			@NotBlank @Size(min = 3, max = 20, message = "Exam name length must be min 2 or max 20 charaters") String examName,
			@NotBlank(message = "please provide date of the exam") String date, String description,
			List<Results> results, List<Questions> questions) {
		super();
		this.examId = examId;
		this.examName = examName;
		this.date = date;
		this.description = description;
		this.results = results;
		this.questions = questions;
	}



	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public List<Questions> getQuestions() {
		return questions;
	}



	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}



	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", examName=" + examName + ", date=" + date + ", description=" + description
				+ ", results=" + results + ", questions=" + questions + "]";
	}
	
	

}
