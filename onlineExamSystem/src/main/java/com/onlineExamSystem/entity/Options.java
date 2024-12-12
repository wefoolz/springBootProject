package com.onlineExamSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opoitnId;
	
	@NotBlank(message="Enter Option Text")
	private String optionText;
	
	@NotBlank(message="The answer is correct or not")
	private boolean isCorrect;
	
	@ManyToOne
	private Questions questions;

	protected Options() {

	}

	protected Options(int opoitnId, @NotBlank(message = "Enter Option Text") String optionText,
			@NotBlank(message = "The answer is correct or not") boolean isCorrect, Questions questions) {
		super();
		this.opoitnId = opoitnId;
		this.optionText = optionText;
		this.isCorrect = isCorrect;
		this.questions = questions;
	}

	public int getOpoitnId() {
		return opoitnId;
	}

	public void setOpoitnId(int opoitnId) {
		this.opoitnId = opoitnId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Options [opoitnId=" + opoitnId + ", optionText=" + optionText + ", isCorrect=" + isCorrect
				+ ", questions=" + questions + "]";
	}
	
	

}
