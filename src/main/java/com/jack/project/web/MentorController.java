package com.jack.project.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@Autowired
	private ReportService reportService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/mentorhomepage", method = RequestMethod.GET)
	public String mentorhomepage(Model model, Principal p) throws IOException {

		List<User> userList = userRepository.findByMentorFalse();
		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();

		model.addAttribute("currentUser", currentUser);
		model.addAttribute("userList", userList);
		model.addAttribute("students", students);
		Collections.sort(students, (p1, p2) -> p1.getUsername().compareTo(p2.getUsername()));

		return "mentorhomepage";
	}

	@RequestMapping(value = "/mentorhomepage/{sort}", method = RequestMethod.GET)
	public String studentsSort(@PathVariable String sort, Model model, Principal p) {

		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();

		if (sort.equals("username")) {
			System.out.println(students);
			Collections.sort(students, (p1, p2) -> p1.getUsername().compareTo(p2.getUsername()));
			System.out.println(students);
		}
		if (sort.equals("school")) {
			System.out.println(students);
			Collections.sort(students, (p1, p2) -> p1.getSchool().compareTo(p2.getSchool()));
			System.out.println(students);
		}
		if (sort.equals("email")) {
			System.out.println(students);
			Collections.sort(students, (p1, p2) -> p1.getEmail().compareTo(p2.getEmail()));
			System.out.println(students);
		}

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("students", students);

		return "mentorhomepage";
	}

	@RequestMapping(value = "/viewusers", method = RequestMethod.GET)
	public String viewUsers(Model model, Principal p) throws IOException {

		List<User> userList = userRepository.findByMentorFalse();
		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();

		model.addAttribute("currentUser", currentUser);
		model.addAttribute("userList", userList);
		model.addAttribute("students", students);

		return "viewusers";
	}

	@RequestMapping(value = "/viewusers/{sort}", method = RequestMethod.GET)
	public String usersSort(@PathVariable String sort, Model model, Principal p) {

		List<User> userList = userRepository.findByMentorFalse();
		;

		if (sort.equals("username")) {
			System.out.println(userList);
			Collections.sort(userList, (p1, p2) -> p1.getUsername().compareTo(p2.getUsername()));
			System.out.println(userList);
		}
		if (sort.equals("school")) {
			System.out.println(userList);
			Collections.sort(userList, (p1, p2) -> p1.getSchool().compareTo(p2.getSchool()));
			System.out.println(userList);
		}
		if (sort.equals("email")) {
			System.out.println(userList);
			Collections.sort(userList, (p1, p2) -> p1.getEmail().compareTo(p2.getEmail()));
			System.out.println(userList);
		}

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("userList", userList);

		return "viewusers";
	}

	@RequestMapping(value = " /addStudent/{userId}", method = RequestMethod.GET)
	public String addStudent(@PathVariable("userId") int id, Model model, Principal p) {

		User currentUser = userService.findByUsername(p.getName());

		User user = userRepository.findById(id);
		String username = user.getUsername();

		System.out.println(username + "saved" + "student: " + username);

		currentUser.saveStudent(user);
		userRepository.save(currentUser);

		return "redirect:/mentor/mentorhomepage";
	}

	@RequestMapping(value = "student/{id}", method = RequestMethod.GET)
	public String getStudent(@PathVariable int id, Model model, Principal p) {

		model.addAttribute("user", userService.findById(id));

		return "student";
	}

	@RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
	public String getReport(@PathVariable int id, Model model, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		Comment commentForm = new Comment();
		Report report = reportService.findById((int) 1);
		List<Comment> comments = commentService.findByReport(report);
		
		model.addAttribute("commentForm",commentForm);
		model.addAttribute("comments", commentService.findByReport(reportService.findById(id)));
		model.addAttribute("report", reportService.findById(id));
		
		return "report";
	}

	@RequestMapping(value = "/mentor/addStudent/{id}", method = RequestMethod.POST)
	public String addStudent1(@PathVariable("userId") int id, Model model, Principal p) {

		User user = userRepository.findById(id);
		User currentUser = userService.findByUsername(p.getName());

		userService.addStudent(user, currentUser);

		return "redirect:/customer/book/{id}";
	}

	@RequestMapping(value = { "/", "/comment/{id}" }, method = RequestMethod.POST)
	public String postComment(@PathVariable int id, Report report, @ModelAttribute("commentForm") Comment commentForm,
			BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "report";
		}
		report = reportService.findById(id);
		String name = principal.getName();
		User mentor = userService.findByUsername(name);
		commentService.save(commentForm, mentor, report);
		reportService.save(report);
		return "redirect:/mentor/report/{id}";
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String search(@RequestParam("searchString") String searchString, Model model, Principal principal) {
		List<User> users = userRepository.findAll();
		List<User> students = new ArrayList<>();
		User currentUser = userService.findByUsername(principal.getName());

		for (User user : users) {
			if (user.getUsername().toLowerCase().contains(searchString.toLowerCase())
					|| user.getFirstname().toLowerCase().contains(searchString.toLowerCase())
					|| user.getLastname().toLowerCase().contains(searchString.toLowerCase())
					|| user.getLastname().toLowerCase().contains(searchString.toLowerCase())) {
				students.add(user);
			}
		}

		model.addAttribute("userList", students);

		return "mentorhomepage";
	}
	
	
}
