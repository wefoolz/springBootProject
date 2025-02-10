package com.contactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactManager.dao.UserRepository;
import com.contactManager.entities.User;
import com.contactManager.helper.Message;

import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact manager");
		
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title", "About - Smart Contact manager");
		
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	//handler for registring user
	@RequestMapping(value="/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result, @RequestParam(value="agreement", defaultValue ="false")boolean agreement, Model model) {
		
		
		
		
		try {
			
			if(result.hasErrors()) {
				System.out.println("Error: "+result);
				model.addAttribute("user", user);
				return "signup";
			}
			
			if(!agreement) {
				System.out.println("You have not agreed the term and conditions");
				throw new Exception("You have not agreed the term and conditions");
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImage("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("aggrement: "+agreement);
			System.out.println(user.toString());
			user = this.userRepository.save(user);
			model.addAttribute("user",new User());
			model.addAttribute("message", new Message("Successfully Registerd!!", "alert-success"));
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			model.addAttribute("message", new Message("Something went wrong!! trye again or try after some time!!"+e.getMessage(), "alert-danger"));
			return "signup";
		}
		
	}
	
	//handler for custome login
	@GetMapping("/signin")
	public String customeLoginn(Model model) {
		
		model.addAttribute("title","LoginPage");  
		return "login";
		
	}
}
