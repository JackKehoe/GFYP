package com.jack.project.validator;

import com.jack.project.model.Report;
import com.jack.project.model.User;
import com.jack.project.service.ReportService;
import com.jack.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ReportValidator implements Validator {
    @Autowired
    private UserService userService;
    private ReportService reportService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Report.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        Report report = (Report) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");
        
    }
}
