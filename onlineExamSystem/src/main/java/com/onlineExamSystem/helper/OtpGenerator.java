package com.onlineExamSystem.helper;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineExamSystem.dao.UserotpRepository;

@Component
public class OtpGenerator {

	@Autowired
	UserotpRepository userotpRepository;
	
	private String otp;

	public String getOtp() {
		Random random = new Random();
        
		int randomnumber = 100000 + random.nextInt(900000);
		otp = Integer.toString(randomnumber);
		return otp;
	}
	

}
