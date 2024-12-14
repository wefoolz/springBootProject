package com.onlineExamSystem.entity.organization;

import jakarta.persistence.Entity;

@Entity
public class OrganizationRecruiter {

    private int organizationRecruiterId;
    private OrganizationAdmin organizationAdminId;
    private Recruiter recruiterId;
}