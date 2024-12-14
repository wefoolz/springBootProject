package com.onlineExamSystem.entity.institute;

import jakarta.persistence.Entity;

@Entity
public class InstituteStudent {
    private int instituteStudentId;
    private Student studentId;
    private InstituteAdmin instituteAdminId;
}
