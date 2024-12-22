package com.onlineExamSystem.entity.institute;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ClassRoomStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classRoomStudentId;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private Student studentId;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private ClassRoom classRoomId;
    
    @OneToMany(mappedBy = "classRoomStudentId")
    private TeacherExamResults results;
}
