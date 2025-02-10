package com.contactManager.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactManager.dao.ContactRepository;
import com.contactManager.dao.UserRepository;
import com.contactManager.entities.Contact;
import com.contactManager.entities.User;
import com.contactManager.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	// method for adding common data to response
	@ModelAttribute
	public void addCommanData(Model model, Principal principal) {
		String name = principal.getName();// getting username
		System.out.println(name + " User name");
		User user = userRepository.getUserByUserName(name);// getting user data
		System.out.println("user " + user);
		model.addAttribute("user", user);
	}

	// user dashboard
	@GetMapping("/index")
	public String dashboard(Model model, Principal principal) {

		
		return "normal/user_dashboard";
	}

	// add contact form handler
	@GetMapping("/add-contact")
	public String openAddContactFrom(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

	// processing add contact form
	@PostMapping("/process-contact")
	public String processContact(@Valid @ModelAttribute Contact contact, BindingResult result,@RequestParam("profileimage") MultipartFile file, Principal principal, Model model, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("Validation Error: "+result.toString());
			System.out.println("Description: "+contact.getDescription());
			return "normal/add_contact_form";
		}
			
		try {
			
		String name = principal.getName();
		
		User user = this.userRepository.getUserByUserName(name);
		
		//processing and uploading file
		if(file.isEmpty()) {
			//if file is empty set default image
			System.out.println("File is empty");
			contact.setImage("contact.png");
		}else {
			//upload the file to folder and update the file name to contact
			LocalDateTime localdatetime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");

	        // Convert to string
	        String datetime = localdatetime.format(formatter);
			System.out.println(datetime);
			System.out.println(user.getId()+datetime+file.getOriginalFilename());
			String imagename = user.getId()+datetime+file.getOriginalFilename();
			contact.setImage(imagename);
	
			File saveFile  = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imagename);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING );
			System.out.println("Image is Uploaded");
		}
		
		//processing user and contact
		user.getContacts().add(contact);
		contact.setUser(user);
		this.userRepository.save(user);
		
		System.out.println("added to database!");
		
		//success message
		Message message =  new Message("Your contact is added", "success");
		model.addAttribute("message", message);
		System.out.println("Data" + contact);
		} catch (Exception e) {
			//error message
	
			Message message =  new Message("Something went wrong! Please Try Again", "danger");
			model.addAttribute("message", message);
			System.out.println("Error: "+e.getMessage());
			return "normal/add_contact_form";
		}
		
		redirectAttributes.addFlashAttribute("message", new Message("Your contact is added successfully!", "success"));
		return "redirect:/user/add-contact";
		
	}

	
	
	
	//show contact handler
	//per page =5[n]
	//current page=0[page]
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page")Integer page, Model m, Principal principal) {
		m.addAttribute("title","Contacts");
		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);
		
		//current page- page
		//contact per page -5
		Pageable pageable =  PageRequest .of(page, 8);
		//get contact list
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
		
		m.addAttribute("contacts",contacts);
		m.addAttribute("currentPage", page);
		
		
		m.addAttribute("totalPages", contacts.getTotalPages()); 
		
		return "normal/show_contacts"; 
	}
	
	//showing particular contact detail
	@GetMapping("/{cid}/contact")
	public String ShowContactDetail(@PathVariable("cid")Integer cid, Model model, Principal principal) {
		System.out.println("cid  "+cid);
		String username = principal.getName();
		User user = this.userRepository.getUserByUserName(username);
		Contact contact =  this.contactRepository.findByUserIDContactId(user.getId(),cid);
		
		
		model.addAttribute("title","Contac Details");
		model.addAttribute("contact", contact);
		return "normal/contact_detail";
	}
	
	
	//Delete perticular contact
	@GetMapping("delete/{cid}")
	public String deleteContact(@PathVariable("cid")Integer cid, Model model, Principal principal, HttpSession session) {
		
		Contact contact = this.contactRepository.findById(cid).get();
		String username = principal.getName();
		User user = this.userRepository.getUserByUserName(username);
		if(user.getId() == contact.getUser().getId()) {
			System.out.println("if"+user.getId());
			System.out.println("if"+contact.getUser().getId());

		
	    try {
	    	System.out.println(contact.getImage());
	    	if(!contact.getImage().equals("contact.png")) {
	    		String image = contact.getImage();
	    		System.out.println(image);
	    		File deleteFile  = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(deleteFile.getAbsolutePath()+File.separator+image);
				System.out.println("Your Stored File Name: "+path);
				boolean b = Files.deleteIfExists(path);
				System.out.println(b);
				System.out.println(Files.exists(path));
	    	}
	    	
	    	//deleting contact there are n number of ways belw 2 ways i have tried
//			1st method by creatind repository with anotation @Transactional and @Modifying
			this.contactRepository.deleteContactById(cid);
	    	
//			2nd method by provideing orphanRemoval=true(User class) and overriding equals method(Contact class)
//	    	user.getContacts().remove(contact);
//	    	this.userRepository.save(user);
	    	
		} catch (IOException e) {
			session.setAttribute("message", new Message("Unable to Delete", "alert-success"));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("message", new Message("Contact Deleted succesfull", "alert-success"));
		}else {
			System.out.println("else"+user.getId());
			System.out.println("else"+contact.getUser().getId());
			session.setAttribute("message", new Message("No Data", "alert-success"));
		}
		return "redirect:/user/show-contacts/0";
	}
	
	//Update contact Form Handler
	@PostMapping("/updateContact/{cid}")
	public String updateForm(@PathVariable("cid")Integer cid,Model model, Principal principal) {
		
		Contact contact = this.contactRepository.findById(cid).get();
		String username = principal.getName();
		User user = this.userRepository.getUserByUserName(username);
		if(user.getId() == contact.getUser().getId()) {
		model.addAttribute("contact", contact);
		model.addAttribute("title","Update Contact");
		return "normal/updateContact";
		}else {
			return "normal/show_contacts";
		}
	}
	
	//update contact processor handler
	@RequestMapping(value="/process-update-contact", method=RequestMethod.POST)
	public String updateContactProcessor(@ModelAttribute Contact contact, Model model, Principal principal, @RequestParam("profileimage")MultipartFile multifile, HttpSession session) {
		
		User user = this.userRepository.getUserByUserName(principal.getName());
		Contact oldContact = this.contactRepository.findById(contact.getCid()).get();

		contact.setUser(user);
		try {
			//image change
			if(!multifile.isEmpty()) {
				
				
				Path path;
				//file work rewrite
				//delete old file
				//if image file is not contact.png
				if(oldContact.getImage()!=null) {
					if(!oldContact.getImage().equals("contact.png")) {
						String oldImageName= oldContact.getImage();
						System.out.println(oldImageName);
			    		File deleteFile  = new ClassPathResource("static/img").getFile();
						path = Paths.get(deleteFile.getAbsolutePath()+File.separator+oldImageName);
						System.out.println("Your Stored File Name: "+path);
						System.out.println(Files.exists(path));
						boolean b = Files.deleteIfExists(path);
						System.out.println(b);
						}
				}
				
				
				
				//write new file
				LocalDateTime localdatetime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");

		        // Convert to string
		        String datetime = localdatetime.format(formatter);
				System.out.println(datetime);
				System.out.println(user.getId()+datetime+multifile.getOriginalFilename());
				String imagename = user.getId()+datetime+multifile.getOriginalFilename();
				contact.setImage(imagename);
		
				File saveFile  = new ClassPathResource("static/img").getFile();
				path = Paths.get(saveFile.getAbsolutePath()+File.separator+imagename);
				Files.copy(multifile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING );
				
			}else {
				contact.setImage(oldContact.getImage());
			}
			
			
			this.contactRepository.save(contact);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("contact name"+ contact.getName());
		System.out.println("contact id "+ contact.getCid());
		session.setAttribute("message", new Message("Your Contact Is Updated", "alert-success"));
		return "redirect:/user/"+contact.getCid()+"/contact";
	}
	
	//delete Session attribute
	@DeleteMapping("/delete-session")
	public ResponseEntity<Boolean> sessionDelete(HttpSession session) {
		System.out.println("Inside sessionDelete handler");
		
		System.out.println("before deleting"+session.getAttribute("message"));
		session.removeAttribute("message");
		System.out.println("after deleting"+session.getAttribute("message"));
		return ResponseEntity.ok(true);
	}
	
	
	
	//profile handler
	@GetMapping("/profile")
	public String profileHandler(Model model) {
		model.addAttribute("title","Profile Page");
		return "normal/profile";
	}
	
	
	
	//update profile form handler
		@PostMapping("/updateprofile")
		public String updateprofile(Model model, Principal principal) {
			
			return "normal/profileupdate";
			
		}
		
		
	//update profile processor handler
		@RequestMapping(value="/process-update-user", method=RequestMethod.POST)
		public String updateUserProfileProcessor(@ModelAttribute User user, Model model, Principal principal, @RequestParam("profileimage")MultipartFile multifile, HttpSession session) {
			
			User olduser = this.userRepository.getUserByUserName(principal.getName());
			user.setPassword(olduser.getPassword());
			user.setEnabled(olduser.isEnabled());
			
			try {
				//image change
				if(!multifile.isEmpty()) {
					
					
					Path path;
					//file work rewrite
					//delete old file
					//if image file is not contact.png
					if(olduser.getImage()!=null) {
						if(!olduser.getImage().equals("default.png")) {
							String oldImageName= olduser.getImage();
							System.out.println(oldImageName);
				    		File deleteFile  = new ClassPathResource("static/img").getFile();
							path = Paths.get(deleteFile.getAbsolutePath()+File.separator+oldImageName);
							System.out.println("Your Stored File Name: "+path);
							System.out.println(Files.exists(path));
							boolean b = Files.deleteIfExists(path);
							System.out.println(b);
							}
					}
					
					
					
					//write new file
					LocalDateTime localdatetime = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");

			        // Convert to string
			        String datetime = localdatetime.format(formatter);
					System.out.println(datetime);
					System.out.println(olduser.getId()+datetime+multifile.getOriginalFilename());
					String imagename = olduser.getId()+datetime+multifile.getOriginalFilename();
					user.setImage(imagename);
			
					File saveFile  = new ClassPathResource("static/img").getFile();
					path = Paths.get(saveFile.getAbsolutePath()+File.separator+imagename);
					Files.copy(multifile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING );
					
				}else {
					user.setImage(olduser.getImage());
				}
				
				
				this.userRepository.save(user);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			System.out.println("user name"+ user.getName());
			System.out.println("user id "+ user.getId());
			session.setAttribute("message", new Message("Your Profile Is Updated", "alert-success"));
			return "/normal/profile";
		}
		
		@PostMapping("delete-profile/")
		public ResponseEntity<Boolean> deleteProfile(Principal principal, HttpSession session) {
			
			
			String username = principal.getName();
			User user = this.userRepository.getUserByUserName(username);
			
		    try {
		    	System.out.println(user.getImage());
		    	if(!user.getImage().equals("contact.png")) {
		    		String image = user.getImage();
		    		System.out.println(image);
		    		File deleteFile  = new ClassPathResource("static/img").getFile();
					Path path = Paths.get(deleteFile.getAbsolutePath()+File.separator+image);
					System.out.println("Your Stored File Name: "+path);
					boolean b = Files.deleteIfExists(path);
					System.out.println(b);
					System.out.println(Files.exists(path));
		    	}
		    	
				this.userRepository.delete(user);
				System.out.println("user deleted");
		    	
			} catch (IOException e) {
				session.setAttribute("message", new Message("Unable to Delete", "alert-success"));
				
				e.printStackTrace();
				return ResponseEntity.ok(false);
			}
			
			session.setAttribute("message", new Message("Contact Deleted succesfull", "alert-success"));
			
			return ResponseEntity.ok(true);
		}
		
}
