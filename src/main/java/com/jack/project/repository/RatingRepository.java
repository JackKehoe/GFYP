package com.jack.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jack.project.model.Rating;
import com.jack.project.model.Report;

public interface RatingRepository extends JpaRepository<Rating, Long>{

	List<Rating> findByReport(Report report);
}

