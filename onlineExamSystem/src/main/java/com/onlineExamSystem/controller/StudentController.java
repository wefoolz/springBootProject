package com.onlineExamSystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineExamSystem.dao.UserRepository;
import com.onlineExamSystem.entity.Users;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	UserRepository userRepository;
	
	// method for adding common data to response
		@ModelAttribute
		public void addCommanData(Model model, Principal principal) {
			String name = principal.getName();// getting username
			System.out.println(name + " User name");
			Users user = userRepository.getUserByUserName(name);// getting user data
			System.out.println("user " + user);
			model.addAttribute("user", user);
		}
	
	@GetMapping("/index")
	public String homepage(Model model, Principal principal) {
		
		return "student/index";
	}
}
