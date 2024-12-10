package com.springtry.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springtry.entity.Email;
import com.springtry.entity.User;
import com.springtry.services.UserDetail;


@RestController
public class UserController {

	
	@Autowired
	public UserDetail usrd;
	
	@GetMapping("/")
	public ModelAndView home(){
		ModelAndView mdv = new ModelAndView();
		mdv.setViewName("index");
		return mdv; 
	}
	
	@GetMapping("/sendEmailForm")
	public ModelAndView email() {
		ModelAndView mdv = new ModelAndView();
		mdv.setViewName("sendEmail");
		return mdv;
	}
	
	//Get all users
	@GetMapping("/users")
	public ResponseEntity<List<User>> allUsers(){
		List<User> li = usrd.getAllUsers();
		
		if(li.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(li);
		
	}
	
	
	//Get user By ID
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> user(@PathVariable("id") int id){
		User u = usrd.getUserByID(id);
		if(u==null) {
			String message = new String("User not found!!");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {return ResponseEntity.status(HttpStatus.OK).body(u);}
	}
	
	//Create New User
	@PostMapping("/addUser/")
	public ResponseEntity<String> addUser(@RequestBody User usr) {
		Boolean b = usrd.addUser(usr);
		
		if(b==false) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("User not Created");
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body("User Created: "+usr.toString());
		
	}
	
	//Delete User By ID
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable("id") int id) {
		if(usrd.isUserAvail(id)==true) {
			this.usrd.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
			
		}
		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
	}
	
	//Update User By Id
	@PutMapping("/updateUser/")
	public ResponseEntity<String> updateUser(@RequestBody User usr){
		
		if(usrd.isUserAvail(usr.getId())==false) {
			
			return ResponseEntity.status(HttpStatus.OK).body("User Not found");
			
		}
		
		
		if(usrd.updateUser(usr)) {
			return ResponseEntity.status(HttpStatus.OK).body("User Updated");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("User Not Updated!!Some Proplem Occure");
	}
	
	
	
	@PostMapping("/email")
	public ResponseEntity<String> sendMail(@RequestParam("file") MultipartFile file, @ModelAttribute Email e) {
//		Email e = new Email();
//		e.setFrom(mail.getFirst("from"));
//		e.setTo(mail.getFirst("to"));
//		e.setSubmessage(mail.getFirst("submessage"));
//		e.setMail(mail.getFirst("mail"));
		boolean f = false;
		System.out.println("1");
		
		System.out.println(e.toString());
		if(file==null||file.getSize()==0) {
			f = this.usrd.sendMail(e);
				System.out.println("without file");
			
		}
		else {
			System.out.println(file.getSize());
			System.out.println("With file");
			f=this.usrd.sendMail(e,file);
			
		}
		System.out.println("16");
		
		if(f) {
			return ResponseEntity.status(HttpStatus.OK).body("Sent!!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("Mail Not Sent!");
		}
	}
}
