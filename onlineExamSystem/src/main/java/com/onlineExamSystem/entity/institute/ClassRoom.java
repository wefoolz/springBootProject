package com.onlineExamSystem.entity.institute;

import jakarta.persistence.Entity;

@Entity
public class ClassRoom {
    private int clssRoomId;
    private String classRoomName;
    private Teacher teacherId;
    private ClassRoomStudent classRoomStudent;
    private InstituteExam exam;
}
