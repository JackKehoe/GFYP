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
	@Autowired
	private RatingService ratingService;
	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/mentorhomepage", method = RequestMethod.GET)
	public String mentorhomepage(Model model, Principal p) throws IOException {

		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();

		model.addAttribute("currentUser", currentUser);
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

		List<User> userList = userService.findByHasMentorFalse();
		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();

		model.addAttribute("currentUser", currentUser);
		model.addAttribute("userList", userList);
		model.addAttribute("students", students);

		return "viewusers";
	}

	@RequestMapping(value = "/viewusers/{sort}", method = RequestMethod.GET)
	public String usersSort(@PathVariable String sort, Model model, Principal p) {

//		List<User> ismentorList = userService.findByMentorFalse();
		List<User> hasmentorList = userService.findByHasMentorFalse();
		
		System.out.println(hasmentorList);
		
		List<User> userList = new ArrayList<User>(hasmentorList);
//		userList.addAll();

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
		
		while(user.isHasMentor() == false) {
			
			//testing
			System.out.println("while statement is being accessed");
			
			user.setHasMentor(true);
			currentUser.saveStudent(user);
			userRepository.save(currentUser);
			userRepository.save(user);

		}

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
		Rating ratingForm = new Rating();
		Report report = reportService.findById((int) id);
		List<Comment> comments = commentService.findByReport(report);
		List<Rating> rating = ratingService.findByReport(report);
		
		model.addAttribute("commentForm",commentForm);
		model.addAttribute("ratingForm", ratingForm);
		model.addAttribute("comments", comments);
		model.addAttribute("rating", rating);
		model.addAttribute("report", report);
		
		return "report";
	}

	@RequestMapping(value = "/mentor/addStudent/{id}", method = RequestMethod.POST)
	public String addStudent1(@PathVariable("userId") int id, Model model, Principal p) {

		User user = userRepository.findById(id);
		User currentUser = userService.findByUsername(p.getName());
		
		user.setMentor(false);

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
	
	@RequestMapping(value = { "/", "/rating/{id}" }, method = RequestMethod.POST)
	public String rateReport(@PathVariable int id, Report report, @ModelAttribute("ratingForm") Rating ratingForm,
			 Model model, Principal principal) {
		
		report = reportService.findById(id);
		User currentUser = userService.findByUsername(principal.getName());
		
		List<Rating> savedRating = report.getRating();
		
		if(savedRating.size() < 1) {
		ratingService.save(ratingForm, report);
		reportService.save(report);
		}
		
		return "redirect:/mentor/report/{id}";
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String search(@RequestParam("searchString") String searchString, Model model, Principal principal) {
		List<User> users = userRepository.findAll();
		List<User> students = new ArrayList<>();
		
		for (User user : users) {
			if (user.getUsername().toLowerCase().contains(searchString.toLowerCase())
					|| user.getFirstname().toLowerCase().contains(searchString.toLowerCase())
					|| user.getLastname().toLowerCase().contains(searchString.toLowerCase())
					|| user.getSchool().toLowerCase().contains(searchString.toLowerCase())) {
				students.add(user);
			}
		}

		model.addAttribute("students", students);
		model.addAttribute("users", users);

		return "mentorhomepage";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model, String error, Principal p) {
		String name = p.getName();
		User currentUser = userService.findByUsername(p.getName());

		model.addAttribute("userForm", userService.findByUsername(name));
		model.addAttribute("currentUser", currentUser);

		return "account";
	}

	@RequestMapping(value = {"/", "/update"}, method = RequestMethod.POST)
	public String update(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			Principal principal) {

        if (bindingResult.hasErrors()) {
            return "account";
        }
		
		String name = principal.getName();
		userService.update(userForm, userService.findByUsername(name));

		return "redirect:/mentor/account";
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public String statistics(Model model, Principal p) throws IOException {
	
		User currentUser = userService.findByUsername(p.getName());
		List<User> students = currentUser.getStudents();
		List<Skill> studentSkills = null;
		List<Skill> allSkills = new ArrayList<Skill>();
		List<Goal> studentGoals = null;
		List<Goal> allGoals = new ArrayList<Goal>();


		for (int i = 0; i < students.size(); i++) {
			String name = students.get(i).getUsername();
			User astudent = userService.findByUsername(name);
			studentSkills = astudent.getSkills();
			allSkills.addAll(studentSkills);
		}
		
		for (int i = 0; i < students.size(); i++) {
			String name = students.get(i).getUsername();
			User astudent = userService.findByUsername(name);
			studentGoals = astudent.getGoals();
			allGoals.addAll(studentGoals);
		}
			
		model.addAttribute("allGoals", allGoals);
		model.addAttribute("allSkills", allSkills);
		model.addAttribute("studentSkills", studentSkills);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("students", students);

		return "statistics";
	}
	
	
}
