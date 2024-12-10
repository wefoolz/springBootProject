package com.springtry.dao;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

import com.springtry.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	// send message with plain text
	public static boolean sendEmail(String message, String subMessage, String from, String to) {
		System.out.println("4");
		// variable for gmail host
		String host = "smtp.gmail.com";

		// get the system properties
		Properties prop = System.getProperties();
		System.out.println(prop);

		// setting important information to properties object
		System.out.println("5");
		// host set
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth", "true");
		System.out.println("6");
		// Step 1: to get the session object
		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				System.out.println("7");
//				return new javax.mail.PasswordAuthentication("parth2503patil@gmail.com","lvhz iime raij eqyw");
				return new javax.mail.PasswordAuthentication("parth253pp@gmail.com", "iwtu nncl jatz dmph");
			}

		});

		session.setDebug(true);

		System.out.println("8");
		// Setp 1: compose the message
		MimeMessage mime = new MimeMessage(session);

		try {
//			//from we have already provided username and password in the Session object so we dont need to provide from
//			mime.setFrom(from);

			System.out.println("9");
			// to
			mime.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			System.out.println("10");
			// subject
			mime.setSubject(subMessage);
			System.out.println("11");
			// message
			mime.setText(message);
			System.out.println("12");
			// send

			// Step 3: send message using transport class

			Transport.send(mime);
			System.out.println("13");

			return true;

		} catch (Exception e) {
			System.out.println("14");
			e.printStackTrace();
			return false;
		}

	}

	// send mail with attachment
	public static boolean sendEMailAttachment(String message, String subMessage, String from, String to,
			MultipartFile file) {

		System.out.println("4");
		// variable for gmail host
		String host = "smtp.gmail.com";

		// get the system properties
		Properties prop = System.getProperties();
		System.out.println(prop);

		// setting important information to properties object
		System.out.println("5");
		// host set
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth", "true");
		System.out.println("6");
		// Step 1: to get the session object
		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				System.out.println("7");
//				return new javax.mail.PasswordAuthentication("parth2503patil@gmail.com","lvhz iime raij eqyw");
				return new javax.mail.PasswordAuthentication("parth253pp@gmail.com", "iwtu nncl jatz dmph");
			}

		});

		session.setDebug(true);

		// Setp 1: compose the message
		MimeMessage mime = new MimeMessage(session);

		try {
//			//from we have already provided username and password in the Session object so we dont need to provide from
//			mime.setFrom(from);

			System.out.println("8");
			// to
			mime.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			System.out.println("9");
			// subject
			mime.setSubject(subMessage);
			System.out.println("10");
			// message

			// Mime multipart
			MimeMultipart multi = new MimeMultipart();

			// text
			MimeBodyPart mtext = new MimeBodyPart();

			// file
			MimeBodyPart mfile = new MimeBodyPart();
			
			System.out.println("11");
			mtext.setText(message);
//			File fl = new File(file.getOriginalFilename());
//			file.transferTo(fl);
//			System.out.println("normal file size"+file.getSize());
//			mfile.attachFile(fl);
			//////////////////////////////
//			InputStream inps = file.getInputStream();
//			
//			mfile.setDataHandler(new javax.activation.DataHandler(new javax.activation.DataSource() {
//
//				@Override
//				public InputStream getInputStream() throws IOException {
//					
//					return inps;
//				}
//
//				@Override
//				public OutputStream getOutputStream() throws IOException {
//					// TODO Auto-generated method stub
//					throw new UnsupportedOperationException("Output Stream Not found");
//				}
//
//				@Override
//				public String getContentType() {
//
//					return file.getContentType();
//				}
//
//				@Override
//				public String getName() {
//					// TODO Auto-generated method stub
//					return file.getOriginalFilename();
//				}}));
			//////////////////////////
			
			 // Create a temporary file
			System.out.println(file.getOriginalFilename());
	        File tempFile = File.createTempFile("File", file.getOriginalFilename());

	        // Transfer the content of MultipartFile to the File
	        file.transferTo(tempFile);
			
			mfile.attachFile(tempFile);
//
			multi.addBodyPart(mtext);
			multi.addBodyPart(mfile);
//
			mime.setContent(multi);
			
//			// send
//
//			// Step 3: send message using transport class
//
			System.out.println("12");
					Transport.send(mime);
			System.out.println("13");

			return true;

		} catch (Exception e) {
			System.out.println("14");
			e.printStackTrace();
			return false;
		}
	};

}
