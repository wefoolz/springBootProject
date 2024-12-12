package com.onlineExamSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	
	private int score;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Exam exam;

	protected Results() {

	}

	protected Results(int resultId, int score, User user, Exam exam) {
		super();
		this.resultId = resultId;
		this.score = score;
		this.user = user;
		this.exam = exam;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "Results [resultId=" + resultId + ", score=" + score + ", user=" + user + ", exam=" + exam + "]";
	}

	
	
}
