package com.jack.project.web;

import com.jack.project.model.*;
import com.jack.project.service.*;
import com.jack.project.validator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SkillService skillService;
	@Autowired
	private GoalService goalService;
	@Autowired
	private CommentService commentService;

	@Autowired
	private UserValidator userValidator;
	@Autowired
	private ReportValidator reportValidator;
	@Autowired
	private SkillValidator skillValidator;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model, Principal principal) throws IOException {
		User currentUser = userService.findByUsername(principal.getName());
		List<Skill> skills = currentUser.getSkills();
		List<Goal> goal = currentUser.getGoals();
		List<Report> savedReports = currentUser.getReports();

		model.addAttribute("skills", skills);
		model.addAttribute("goal", goal);
		model.addAttribute("savedReports", savedReports);
		model.addAttribute("currentUser", currentUser);

		return "welcome";
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

		return "redirect:/student/account";
	}

	@RequestMapping(value = { "/addreport" }, method = RequestMethod.GET)
	public String report(Model model) {
		model.addAttribute("reportForm", new Report());
		return "addreport";
	}

	@RequestMapping(value = "/addreport", method = RequestMethod.POST)
	public String report(@ModelAttribute("reportForm") Report reportForm, BindingResult bindingResult, Model model,
			Principal principal) {
		reportValidator.validate(reportForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "report";
		}

		String name = principal.getName();
		reportService.save(reportForm, name);
		User currentUser = userService.findByUsername(principal.getName());
		currentUser.addReport(reportForm);

		List<Report> savedReports = currentUser.getReports();
		model.addAttribute("savedReports", savedReports);
		model.addAttribute("currentUser", currentUser);

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
	public String getReport(@PathVariable int id, Model model, Principal p) {
		
		Report report = reportService.findById(id);
		report.getComments();
		
		model.addAttribute("commentForm", new Comment());
		model.addAttribute("report", reportService.findById(id));
		model.addAttribute("comments", commentService.findByReport(report));
		return "report";
	}

	@RequestMapping(value = "/report/edit/{id}", method = RequestMethod.GET)
	public String get(@PathVariable int id, Model model, Principal p) {
		model.addAttribute("reportForm", reportService.findById(id));

		System.out.print(reportService.findById(id).getUploadFile());

		return "reportedit";
	}

	@RequestMapping(value = "/report/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("reportForm") Report report, Model model, Principal p) {
		report.setUploadFile(reportService.findById(report.getId()).getUploadFile());
		
		User currentUser = userService.findByUsername(p.getName());
		String cuserName = currentUser.getUsername();
		
		System.out.println(report.getUploadFile());
		reportService.save(report, cuserName);
		return "reportedit";
	}

	@RequestMapping(value = "/add/{id}/uploadFile", method = RequestMethod.POST)
	public String addFile(@RequestPart("uploadFile") MultipartFile file, @PathVariable int id, Model model, Principal p)
			throws IllegalStateException, IOException {
		User currentUser = userService.findByUsername(p.getName());
		String name = p.getName();

		Report report = reportService.findById(id);
		String fileName = report.getId() + file.getOriginalFilename();
		String fileUploadPath = "C:\\Users\\jackk\\eclipse-workspace\\GFYP1\\src\\main\\webapp\\resources\\files\\"
				+ fileName;
		System.out.println(fileUploadPath);
		file.transferTo(new File(fileUploadPath));
		report.setUploadFile(fileName);

		reportService.save(report, name);

		return "redirect:/student/report/{id}";
	}

	@RequestMapping(value = { "/", "/skill" }, method = RequestMethod.GET)
	public String skill(Model model) {
		model.addAttribute("skillForm", new Skill());
		return "skill";
	}

	@RequestMapping(value = "/skill", method = RequestMethod.POST)
	public String skill(@ModelAttribute("skillForm") Skill skillForm, BindingResult bindingResult, Model model,
			Principal principal) {
		skillValidator.validate(skillForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "skill";
		}

		String name = principal.getName();
		User currentUser = userService.findByUsername(principal.getName());

		System.out.println(skillForm.getSkillName());
		System.out.println(skillForm.getCategory());
		System.out.println(currentUser.getSkills().size());

		List<Skill> savedSkills = currentUser.getSkills();

		if (savedSkills.size() < 2) {
			model.addAttribute("savedSkills", savedSkills);
			model.addAttribute("currentUser", currentUser);
			currentUser.addSkill(skillForm);
			skillService.save(skillForm, name);
		} else {
			model.addAttribute("error", "Max number of skills already.");
		}
		return "redirect:/welcome";

	}

	@RequestMapping(value = "/skill/{id}", method = RequestMethod.GET)
	public String geSkill(@PathVariable int id, Model model, Principal p) {
		model.addAttribute("skill", skillService.findById(id));
		return "askill";
	}

	@RequestMapping(value = "/skill/edit/{id}", method = RequestMethod.GET)
	public String editSkill(@PathVariable int id, Model model, Principal p) {
		model.addAttribute("skillForm", skillService.findById(id));

		return "skilledit";
	}

	@RequestMapping(value = "/skill/edit/{id}", method = RequestMethod.POST)
	public String updateSkill(@ModelAttribute("skillForm") Skill skill, Model model, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		String cuserName = currentUser.getUsername();
		skillService.save(skill, cuserName);
		return "skilledit";
	}

	@RequestMapping(value = { "/goal" }, method = RequestMethod.GET)
	public String goal(Model model) {
		model.addAttribute("goalForm", new Goal());
		return "goal";
	}

	@RequestMapping(value = "/goal", method = RequestMethod.POST)
	public String goal(@ModelAttribute("goalForm") Goal goalForm, BindingResult bindingResult, Model model,
			Principal principal) {

		User currentUser = userService.findByUsername(principal.getName());
		String name = principal.getName();

		List<Goal> savedGoal = currentUser.getGoals();
		
		if(savedGoal.size() < 1) {
			model.addAttribute("savedGoal", savedGoal);
			model.addAttribute("currentUser", currentUser);
			goalService.save(goalForm, name);
		}
		

		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/goal/{id}", method = RequestMethod.GET)
	public String getGoal(@PathVariable int id, Model model, Principal p) {
		model.addAttribute("goal", goalService.findById(id));
		return "agoal";
	}

	@RequestMapping(value = "/goal/edit/{id}", method = RequestMethod.GET)
	public String editGoal(@PathVariable int id, Model model, Principal p) {
		model.addAttribute("goalForm", goalService.findById(id));

		return "goaledit";
	}

	@RequestMapping(value = "/goal/edit/{id}", method = RequestMethod.POST)
	public String updateGoal(@ModelAttribute("goalForm") Goal goal, Model model, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		String cuserName = currentUser.getUsername();
		goalService.save(goal, cuserName);
		return "goaledit";
	}

}
