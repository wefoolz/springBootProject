package com.onlineExamSystem.entity.institute;

import java.util.List;

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

@Entity
@Table(name = "classroomstudent")
public class ClassRoomStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomstudentid;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private Users studentid;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
    private ClassRoom classroomid;
    
    @OneToMany(mappedBy = "classroomstudentid")
    private List<TeacherExamResults> results;
}
