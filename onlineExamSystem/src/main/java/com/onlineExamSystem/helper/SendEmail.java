package com.onlineExamSystem.helper;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.onlineExamSystem.entity.Email;

public class SendEmail {

	public boolean send(String to, String otp, String name) {
		boolean result = false;
		Email e = new Email();
		e.setTo(to);
		e.setFrom("parth253pp@gmail.com");
		e.setSubmessage("Online Exam System OTP Verification");
		e.setMail("Hello !!"+name+" \n Welcome to the Online Exam Systen Platform \n"
		+"Please Use This One Time Password For OtP Verification: \n OTP="+otp+"\n This OTP is only vaild for 5 minutes."
				+" \n Do not Share Credentials and security informations like username, password and OTP with any one."
		+" We’re here to support you, but we cannot take responsibility for mistakes beyond our control.");
		
		
		String host = "smtp.gmail.com";
		
		Properties prop = System.getProperties();
		System.out.println(prop);
		
		
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth", "true");
		
		
		
		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				
//				return new javax.mail.PasswordAuthentication("parth2503patil@gmail.com","lvhz iime raij eqyw");
				return new javax.mail.PasswordAuthentication(e.getFrom(), "iwtu nncl jatz dmph");
			}

		});
		
		
		session.setDebug(true);
		
		
		MimeMessage mime = new MimeMessage(session);
		try {
			
			mime.setRecipient(Message.RecipientType.TO, new InternetAddress(e.getTo()));
			
			mime.setSubject(e.getSubmessage());
			
			mime.setText(e.getMail());
			
			Transport.send(mime);
			System.out.println("mail sent");
			result = true;
			
		}catch(Exception exc) {
			exc.printStackTrace();
			System.err.println("Exception Occer!!!!"+exc.getMessage());
		}
		
		
		return result;
	}
}
