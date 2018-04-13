package com.jack.project.service;

import com.jack.project.model.Goal;
import com.jack.project.model.User;
import com.jack.project.repository.GoalRepository;
import com.jack.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {
	
    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public void save(Goal goal, String name) {
    	User user = userRepository.findByUsername(name);
    	goal.setGoalName(goal.getGoalName());
    	goal.setUser(user);
    	goalRepository.save(goal);
    }

    @Override
    public Goal findById(int id) {
        return goalRepository.findById(id);
    }
}