package com.onlineExamSystem.entity.institute;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="teacherexamresults")
public class TeacherExamResults{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultid;
	
	private String result;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private TeacherExam examid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ClassRoomStudent classroomstudentid;

}
