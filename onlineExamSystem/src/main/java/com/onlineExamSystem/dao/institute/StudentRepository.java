package com.onlineExamSystem.dao.institute;

import com.onlineExamSystem.entity.institute.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
