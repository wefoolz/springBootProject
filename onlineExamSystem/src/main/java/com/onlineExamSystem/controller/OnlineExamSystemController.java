package com.onlineExamSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlineExamSystem.dao.UserRepository;
import com.onlineExamSystem.dao.UserotpRepository;
import com.onlineExamSystem.entity.Userotp;
import com.onlineExamSystem.entity.Users;
import com.onlineExamSystem.helper.Message;
import com.onlineExamSystem.helper.OtpGenerator;
import com.onlineExamSystem.helper.SendEmail;
import com.onlineExamSystem.helper.ValidatePassword;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/online_exam_system")
public class OnlineExamSystemController {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	OtpGenerator otpGenerat;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserotpRepository userotpRepository;

	@Autowired
	ValidatePassword validatepassword;

	Thread t;

	// Open Home Page
	@GetMapping("/home")
	public String home(Model model) {

		model.addAttribute("users", new Users());
		model.addAttribute("title", "OnlineExamSystem-HomePae");

		return "home";
	}

	// Validate fields that are required at the time of registration
	@PostMapping("/validate_fields")
	public String validatFields(@Valid @ModelAttribute("users") Users user, BindingResult result, Model model,
			RedirectAttributes redirectAttribute, HttpSession session) {
		Userotp userotp = new Userotp();
		if (result.hasErrors()) {
			model.addAttribute("users", user);
			model.addAttribute("message", new Message("Validation Error! Please Enter Proper Data!", "alert-danger"));
			model.addAttribute("modal", "#userModal");
			return "home";
		} else if (!validatepassword.validateUserPassword(user.getPassword())) {

			model.addAttribute("users", user);
			model.addAttribute("message", new Message(
					"Password Must Contain Atleas 8 charcacters! \n Must Contain Atleast One Capital And One Lower Case Alphabate! \n Must Contain Atleast One Special Char \n Must Contain Atleas On Digit!",
					"alert-danger"));
			model.addAttribute("modal", "#userModal");
		} else if (userRepository.getUserByUserName(user.getUsername()) != null) {
			model.addAttribute("users", user);
			model.addAttribute("message",
					new Message("UserName Already Exist Please Use Differnt User Name!!", "alert-danger"));
			model.addAttribute("modal", "#userModal");
			return "home";
		} else if (userRepository.getUserByEmail(user.getEmailid()) != null) {
			model.addAttribute("users", user);
			model.addAttribute("message",
					new Message("Email Address already Registered With Differen Account. Please Use Differen Email!!",
							"alert-danger"));
			model.addAttribute("modal", "#userModal");
			return "home";
		}

		try

		{

			// generate otp
			String otp = otpGenerat.getOtp();
			
			

			// save otp

			userotp.setOtp(otp);
			userotp.setUsername(user.getUsername());
			userotpRepository.deleteUserByUserName(userotp.getUsername());

			userotpRepository.save(userotp);

			// delete otp after 5 minutes
			Runnable deleteOtpTask = () -> {
				try {
					Thread.sleep(60000); // 5 minutes

					userotpRepository.deleteUserByUserName(userotp.getUsername());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			};

			// sent otp
			SendEmail sendemail = new SendEmail();
			if (!sendemail.send(user.getEmailid(), otp, user.getFname())) {
				userotpRepository.deleteUserByUserName(userotp.getUsername());
				model.addAttribute("users", user);
				model.addAttribute("message",
						new Message("Something Went Wrong!! Please Trye Again Or After Some Time", "alert-danger"));
				model.addAttribute("modal", "#userModal");
				return "home";
			}

			// Run the task in a background thread
			t = new Thread(deleteOtpTask);
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
			userotpRepository.deleteUserByUserName(userotp.getUsername());
			model.addAttribute("users", user);
			model.addAttribute("message",
					new Message("Something Went Wrong!! Please Trye Again Or After Some Time", "alert-danger"));
			model.addAttribute("modal", "#userModal");
			return "home";

		}

		redirectAttribute.addAttribute("modal", "#otpModal");
		session.setAttribute("user", user);
		return "redirect:/online_exam_system/home";
	}

	@PostMapping("/signup")
	public String signup(@RequestParam("otp1") String otp1, @RequestParam("otp2") String otp2,
			@RequestParam("otp3") String otp3, @RequestParam("otp4") String otp4, @RequestParam("otp5") String otp5,
			@RequestParam("otp6") String otp6, Model model, RedirectAttributes redirectAttributes,
			HttpSession session) {

		String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
		Users user = (Users) session.getAttribute("user");
		Userotp stored = new Userotp();
		stored = userotpRepository.getUserByUserName(user.getUsername());
		if (stored == null) {
			model.addAttribute("users", user);
			model.addAttribute("message", new Message("Time Exceded Please Regenerate OTP!", "alert-danger"));
			model.addAttribute("modal", "#userModal");
			return "home";
		}

		boolean b = otp.equals(stored.getOtp());
		if (!b) {
			model.addAttribute("users", user);
			model.addAttribute("message", new Message("Invalid OTP!", "alert-danger"));
			model.addAttribute("modal", "#otpModal");
			return "home";

		}

		t.interrupt();
		try {

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("users", user);
			model.addAttribute("message",
					new Message("Something Went Wrong!! Please Trye Agina Or After Some Time!!", "alert-danger"));
			
			return "home";
		}

		userotpRepository.deleteUserByUserName(stored.getUsername());
		redirectAttributes.addAttribute("modal", "#loginModal");
		redirectAttributes.addFlashAttribute("message",
				new Message("User Registered Succefully!! Please Login!", "alert-success"));

		return "redirect:/online_exam_system/home";
	}

	@GetMapping("/check_username")
	public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam String username) {
		boolean exists = userRepository.getUserByUserName(username) != null;
		return ResponseEntity.ok(exists);
	}

	@GetMapping("/check_email")
	public ResponseEntity<Boolean> checkemailAvailability(@RequestParam String emailid) {
		boolean exists = userRepository.getUserByEmail(emailid) != null;
		return ResponseEntity.ok(exists);
	}
}
