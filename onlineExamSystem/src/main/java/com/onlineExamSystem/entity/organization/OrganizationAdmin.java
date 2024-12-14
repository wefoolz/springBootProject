package com.onlineExamSystem.entity.organization;

import jakarta.persistence.Entity;

@Entity
public class OrganizationAdmin {

    private int adminId;
    private String AdminNameF;
    private String AdminNameL;
    private String userName;
    private String password;
    private String emailId;
    private int organizationId;
    private String organizationName;
    private String organizationAddress;
    private String organizationMail;
    private Recruiter recruiter;

}
