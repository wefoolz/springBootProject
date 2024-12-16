package com.onlineExamSystem.dao.institute;

import com.onlineExamSystem.entity.institute.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
