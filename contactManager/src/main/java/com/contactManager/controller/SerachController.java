package com.contactManager.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contactManager.dao.ContactRepository;
import com.contactManager.dao.UserRepository;
import com.contactManager.entities.Contact;
import com.contactManager.entities.User;

@RestController
public class SerachController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	//search Handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> serach(@PathVariable("query") String query, Principal principal){
		System.out.println(query);
		User user = this.userRepository.getUserByUserName(principal.getName());
		List<Contact> contacts = this.contactRepository.findByNameContainingAndUser(query, user);
		
		return ResponseEntity.ok(contacts);
	}
}
