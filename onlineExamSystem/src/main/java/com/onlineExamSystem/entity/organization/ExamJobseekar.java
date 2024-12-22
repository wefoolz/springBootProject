package com.onlineExamSystem.entity.organization;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onlineExamSystem.entity.institute.ClassRoom;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ExamJobseekar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examJobseekertId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Jobseeker jobseekerId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private RecruiterExam examId;
    
    @OneToMany(mappedBy = "examJobseekarId")
    @JsonBackReference
    private List<RecruiterExamResults> results;
}
