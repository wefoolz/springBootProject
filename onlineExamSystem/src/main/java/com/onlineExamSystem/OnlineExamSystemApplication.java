package com.onlineExamSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlineExamSystem.dao.UserotpRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class OnlineExamSystemApplication {
	@Autowired
	private UserotpRepository userotpRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineExamSystemApplication.class, args);
	}

	
	/*
	 * Deleteing OTP records if remain undeleted in the database befor its shut down
	 */
	@PostConstruct
    public void clearUserOtpRepository() {
        userotpRepository.deleteAllInBatch();
        System.out.println("All OTP records have been deleted.");
    }
}
