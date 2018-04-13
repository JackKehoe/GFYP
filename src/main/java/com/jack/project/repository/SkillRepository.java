package com.jack.project.repository;

import com.jack.project.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long>{

	Skill findById(int id);
}
