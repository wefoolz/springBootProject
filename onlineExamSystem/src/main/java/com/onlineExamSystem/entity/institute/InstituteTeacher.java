package com.onlineExamSystem.entity.institute;

import jakarta.persistence.Entity;

@Entity
public class InstituteTeacher {
    private int instituteTeacherId;
    private Teacher teacherId;
    private InstituteAdmin instituteId;
}
