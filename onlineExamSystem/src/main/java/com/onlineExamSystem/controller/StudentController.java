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
			Users user = userRepository.getUserByUserName(name);// getting user data
			model.addAttribute("user", user);
		}
	
//	@GetMapping("/index")
//	public String homepage(Model model, Principal principal) {
//		
//		return "student/index";
//	}
	
	//Student Home Page handler
	@GetMapping("/index")
	public String dashboardpage(Model model, Principal principal) {
		model.addAttribute("title","Home Page");
		return "student/index";
	}
	
	
	//Student Pre_Exam handler
	@GetMapping("/previousexam")
	public String preExamPage(Model model){
		model.addAttribute("title","Previous Exams");
		return "student/Pre_Exam";
	}
	
	//Student SProfile handler
	@GetMapping("/profile")
	public String spprofilePage(Model model){
		model.addAttribute("title","Profile");
		return "student/SProfile";
	}
	//Student Student_Stat handler
	@GetMapping("/studentstats")
	public String studentStatPage(Model model){
		model.addAttribute("title","Stats");
		return "student/Student_Stat";
	}
	//Student Unattempted handler
	@GetMapping("/unattempted")
	public String unattemptedPage(Model model){
		model.addAttribute("title","UnAttempted Exams");
		return "student/Unattempted";
	}
	//Student Upcoming handler
	@GetMapping("/upcomingexams")
	public String upcomingPage(Model model){
		model.addAttribute("title","Upcoming Exams");
		return "student/Upcoming";
	}
}
