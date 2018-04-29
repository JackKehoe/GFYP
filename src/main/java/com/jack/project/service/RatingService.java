package com.jack.project.service;

import java.util.List;

import com.jack.project.model.Report;
import com.jack.project.model.Rating;
import com.jack.project.model.User;

public interface RatingService {
	
	void save(Rating rating, Report report);

	List<Rating> findByReport(Report report);
	
}