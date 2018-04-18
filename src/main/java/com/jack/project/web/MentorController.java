package com.jack.project.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jack.project.model.*;
import com.jack.project.repository.UserRepository;
import com.jack.project.service.*;
import com.jack.project.validator.UserValidator;

@Controller
@RequestMapping("/mentor")
public class MentorController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/mentorhomepage", method = RequestMethod.GET)
	public String mentorhomepage(Model model, Principal p) throws IOException {

		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("students", students);

		return "mentorhomepage";
	}
	
	@RequestMapping(value = " /addStudent/{userId}", method=RequestMethod.GET)
	public String addStudent(@PathVariable("userId")int id, Model model, Principal p) {

		User currentUser = userService.findByUsername(p.getName());		


		User user = userRepository.findById(id);
		String username = user.getUsername();

		System.out.println(username + "saved" + "student: " + username);
		
		currentUser.saveStudent(user);
		userRepository.save(currentUser);

		return "addStudent";
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public String getStudent(@PathVariable int id, Model model, Principal p) {
		
		model.addAttribute("user", userService.findById(id));
		
		return "student";
	}
	
	@RequestMapping(value = "/mentor/addStudent/{id}", method = RequestMethod.POST)
	public String addStudent1(@PathVariable("userId")int id, Model model, Principal p) {

		User user = userRepository.findById(id);
		User currentUser = userService.findByUsername(p.getName());

		userService.addStudent(user, currentUser);

		return "redirect:/customer/book/{id}";
	}

}
