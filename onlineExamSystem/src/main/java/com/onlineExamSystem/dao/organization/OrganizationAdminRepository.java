package com.onlineExamSystem.dao.organization;

import com.onlineExamSystem.entity.organization.OrganizationAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationAdminRepository extends JpaRepository<OrganizationAdmin, Integer> {
}
