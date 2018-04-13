package com.jack.project.repository;

import com.jack.project.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long>{

	Goal findById(int id);
}
