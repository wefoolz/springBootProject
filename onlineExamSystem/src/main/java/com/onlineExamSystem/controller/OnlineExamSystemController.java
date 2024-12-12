package com.onlineExamSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnlineExamSystemController {

	@GetMapping("/demo")
	public String demo(Model model) {
		model.addAttribute("name","ram");
		return "user/demo";
		
	}
}
