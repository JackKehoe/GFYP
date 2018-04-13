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
	private UserValidator userValidator;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model, Principal p) throws IOException {

		User currentUser = userService.findByUsername(p.getName());
		model.addAttribute("currentUser", currentUser);

		return "welcome";
	}

}
