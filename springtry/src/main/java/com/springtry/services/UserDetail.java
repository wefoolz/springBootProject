package com.springtry.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.springtry.dao.UserRepository;
import com.springtry.entity.Email;
import com.springtry.entity.User;

@Component
public class UserDetail {

	@Autowired
	public UserRepository usr;
	
	
	//Is user available
	public boolean isUserAvail(int id) {
		
		if(this.usr.findById(id)!=null) {
			return true;
		}
		else return false;
	}
	
	//fetch All users
	public List<User> getAllUsers() {
		List<User> li = (List<User>)this.usr.findAll();
		return li;
	}
	
	
	
	//fetch user By Id
	public User getUserByID(int id) {
		
		
		User u = null;
		try {
			Optional<User> user = this.usr.findById(id);
			u = user.get();
			return u;
		}catch(Exception e) {
			return u;
		}
		
	}

	//ass new user
	public Boolean addUser(User user) {
		boolean f = false;
		
		try {
			this.usr.save(user);
			f=true;
		}catch(Exception e) {
			
		}
		return f;
	}
	
	//delete user by id
	public void deleteUser(int id) {
		this.usr.deleteById(id);
		
	}
	
	//update user by id
	public boolean updateUser(User u) {
		try {
			this.usr.save(u);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	//send plain text message
	public boolean sendMail(Email e) {
		
		System.out.println("2");
		String message = e.getMail();
		String subMessage = e.getSubmessage();
		String to = e.getTo();
		String from = e.getFrom();
		boolean f = false;
		System.out.println("3");
		System.out.println("without attachment");
		f = UserRepository.sendEmail(message,subMessage,from, to);
		
		System.out.println("15");
		return f;
	}

	//send attachment
	public boolean sendMail(Email e, MultipartFile file) {
		System.out.println("2");
		String message = e.getMail();
		String subMessage = e.getSubmessage();
		String to = e.getTo();
		String from = e.getFrom();
		boolean f = false;
		System.out.println("3");
		System.out.println("with attachment");
		f = UserRepository.sendEMailAttachment(message, subMessage, from, to, file);
		System.out.println("15");
		return f;
	}
	
}
