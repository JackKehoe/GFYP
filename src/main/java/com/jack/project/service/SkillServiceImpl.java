package com.jack.project.service;

import com.jack.project.model.Report;
import com.jack.project.model.Skill;
import com.jack.project.model.User;
import com.jack.project.repository.SkillRepository;
import com.jack.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {
	
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public void save(Skill skill, String name) {
    	User user = userRepository.findByUsername(name);
    	skill.setSkillName(skill.getSkillName());
    	skill.setCategory(skill.getCategory());
    	skill.setUser(user);
    	skillRepository.save(skill);
    }

    @Override
    public Skill findById(int id) {
        return skillRepository.findById(id);
    }
    
    @Override
	public void save(Skill skill) {
		
    	skill.setSkillName(skill.getSkillName());
    	skill.setCategory(skill.getCategory());
    	skillRepository.save(skill);
	}
}

