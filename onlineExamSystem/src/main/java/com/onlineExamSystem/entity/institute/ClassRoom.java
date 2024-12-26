package com.onlineExamSystem.entity.institute;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onlineExamSystem.entity.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "classroom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomid;
    
    @NotBlank
    @Size(min = 3, message = "must contain minimum 3 characters")
    private String classroomname;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private Users teacherid;
    
    @OneToMany(mappedBy = "classroomid")
    @JsonBackReference
    private List<TeacherExam> exam;
    
    @OneToMany(mappedBy = "classroomid")
    @JsonBackReference
    private List<ClassRoomStudent> classroomstudent;
    
    
}
