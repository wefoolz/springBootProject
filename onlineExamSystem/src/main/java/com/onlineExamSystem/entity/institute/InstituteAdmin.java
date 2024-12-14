package com.onlineExamSystem.entity.institute;

import jakarta.persistence.Entity;

@Entity
public class InstituteAdmin {

    private int adminId;
    private String AdminNameF;
    private String AdminNameL;
    private String userName;
    private String password;
    private String emailId;
    private int instituteId;
    private String InstituteName;
    private String instituteAddress;
    private String instituteMail;
    private Teacher teacher;
    private Student student;




}
