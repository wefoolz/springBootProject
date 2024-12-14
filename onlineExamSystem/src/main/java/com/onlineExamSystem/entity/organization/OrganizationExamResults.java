package com.onlineExamSystem.entity.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class OrganizationExamResults {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	
	private int result;

	private ExamJobseekar examJobseekarId;

}
