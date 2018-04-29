package com.jack.project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.project.model.Report;
import com.jack.project.model.Rating;
import com.jack.project.model.User;
import com.jack.project.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
    @Autowired
    private RatingRepository ratingRepository;

    
	
	@Override
    public void save(Rating rating, Report report) {
        rating.setReportRating(rating.getReportRating());
		rating.setReport(report);
        ratingRepository.save(rating);
    }
	
	@Override
	public List<Rating> findByReport(Report report){
		return ratingRepository.findByReport(report);
	}
	
}