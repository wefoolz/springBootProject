package com.onlineExamSystem.dao.organization;

import com.onlineExamSystem.entity.organization.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
}
