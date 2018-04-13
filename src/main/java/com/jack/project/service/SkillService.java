package com.jack.project.service;

import com.jack.project.model.Skill;

public interface SkillService {
    void save(Skill skill, String name);

   	Skill findById(int id);
}