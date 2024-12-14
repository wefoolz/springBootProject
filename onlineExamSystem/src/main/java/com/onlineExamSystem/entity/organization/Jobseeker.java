package com.onlineExamSystem.entity.organization;

import jakarta.persistence.Entity;

@Entity
public class Jobseeker {
private int jobseekerId;
private String jobseekerNameF;
private String jobseekerNameL;
private String userName;
private String password;
private String emailId;
private ExamJobseekar examJobseekar;
}
