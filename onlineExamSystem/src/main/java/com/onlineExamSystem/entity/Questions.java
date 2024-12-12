package com.onlineExamSystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@NotBlank(message="please enter Question")
	private String questionText;
	
	@ManyToOne
	private Exam exam;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questions")
	private Options options;

	protected Questions() {

	}

	protected Questions(int questionId, @NotBlank(message = "please enter Question") String questionText, Exam exam,
			Options options) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.exam = exam;
		this.options = options;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", questionText=" + questionText + ", exam=" + exam
				+ ", options=" + options + "]";
	}

	
	
	
}