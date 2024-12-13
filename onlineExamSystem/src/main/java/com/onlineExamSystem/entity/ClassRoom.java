package com.onlineExamSystem.entity;

import jakarta.persistence.Entity;

@Entity
public class ClassRoom {
    private int clssRoomId;
    private String classRoomName;
    private Teacher teacherId;
    private ClassRoomStudent classRoomStudent;
    private Exam exam;
}
