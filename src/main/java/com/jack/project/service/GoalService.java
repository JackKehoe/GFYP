package com.jack.project.service;

import com.jack.project.model.Goal;

public interface GoalService {
    void save(Goal goal, String name);

   	Goal findById(int id);
}
