package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class RecruiterExamResults {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	
	private int result;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ExamJobseekar examJobseekarId;

}
