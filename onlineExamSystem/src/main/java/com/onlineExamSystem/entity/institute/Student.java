package com.onlineExamSystem.entity.institute;

import jakarta.persistence.Entity;

@Entity
public class Student {
private int studentId;
private String studentNameF;
private String studentNameL;
private String userName;
private String password;
private String emailId;
private InstituteAdmin instituteId;
private ClassRoomStudent classRoomStudent;
}
