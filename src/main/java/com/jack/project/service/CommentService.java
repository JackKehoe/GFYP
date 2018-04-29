package com.jack.project.service;

import java.util.List;

import com.jack.project.model.Report;
import com.jack.project.model.Comment;
import com.jack.project.model.User;

public interface CommentService {
	
	void save(Comment comment, User user, Report report);

	List<Comment> findByReport(Report report);
	
	

}