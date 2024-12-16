package com.onlineExamSystem.dao.organization;

import com.onlineExamSystem.entity.organization.Jobseeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Integer> {
}
