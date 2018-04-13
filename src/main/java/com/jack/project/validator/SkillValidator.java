package com.jack.project.validator;

import com.jack.project.model.Skill;
import com.jack.project.model.User;
import com.jack.project.service.SkillService;
import com.jack.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SkillValidator implements Validator {
    @Autowired
    private UserService userService;
    private SkillService skillService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Skill.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
    	Skill skill = (Skill) o;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skillName", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        
    }
}