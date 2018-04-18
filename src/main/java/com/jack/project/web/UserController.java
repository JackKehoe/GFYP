package com.jack.project.web;

import com.jack.project.model.*;
import com.jack.project.service.*;
import com.jack.project.validator.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private UserValidator userValidator; 
    @Autowired
    private ReportValidator reportValidator;
    @Autowired
    private SkillValidator skillValidator;
    

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) throws IOException{
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
    
   
    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String report(Model model) {
        model.addAttribute("reportForm", new Report());
        return "report";
    }
    
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String report(@ModelAttribute("reportForm") Report reportForm, BindingResult bindingResult, Model model, Principal principal) {
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
    
    
    
    
    @RequestMapping(value = {"/", "/skill"}, method = RequestMethod.GET)
    public String skill(Model model) {
        model.addAttribute("skillForm", new Skill());
        return "skill";
    }
    
    @RequestMapping(value = "/skill", method = RequestMethod.POST)
    public String skill(@ModelAttribute("skillForm") Skill skillForm, BindingResult bindingResult, Model model, Principal principal) {
        skillValidator.validate(skillForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "skill";
        }
        
        // Adds skill object to database and adding it to Users skill list
        String name = principal.getName();
        skillService.save(skillForm, name);
        User currentUser = userService.findByUsername(principal.getName());
        currentUser.addSkill(skillForm);
        // Debug here shows that the list contains correct data and the list size
        System.out.println(skillForm.getSkillName());
        System.out.println(skillForm.getCategory());
        System.out.println(currentUser.getSkills().size());
        
        // Attempting to pass the list to NewFile.jsp to be displayed
        List<Skill> savedSkills = currentUser.getSkills();
        for(int i = 0; i < savedSkills.size(); i++) {
        	 model.addAttribute("savedSkills", savedSkills);
             model.addAttribute("currentUser", currentUser);
        }
        
        return "redirect:/welcome";
    }
    
    @RequestMapping(value = {"/goal"}, method = RequestMethod.GET)
    public String goal(Model model) {
        model.addAttribute("goalForm", new Goal());
        return "goal";
    }
    
    @RequestMapping(value = "/goal", method = RequestMethod.POST)
    public String goal(@ModelAttribute("goalForm") Goal goalForm, BindingResult bindingResult, Model model, Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());
        String name = principal.getName();
        goalService.save(goalForm, name);
        
        List<Goal> savedGoal = currentUser.getGoals();
        model.addAttribute("savedGoal", savedGoal);
        model.addAttribute("currentUser", currentUser);

        return "redirect:/welcome";
    }

}
