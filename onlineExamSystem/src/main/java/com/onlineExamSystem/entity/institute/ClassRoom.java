package com.onlineExamSystem.entity.institute;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clssRoomId;
    
    @NotBlank
    @Size(min = 3, message = "must contain minimum 3 characters")
    private String classRoomName;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private Teacher teacherId;
    
    @OneToMany(mappedBy = "classRoomId")
    @JsonBackReference
    private List<TeacherExam> exam;
    
    @OneToMany(mappedBy = "classRoomId")
    @JsonBackReference
    private ClassRoomStudent classRoomStudent;
    
    
}
