package com.jack.project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.project.model.Report;
import com.jack.project.model.Comment;
import com.jack.project.model.User;
import com.jack.project.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
    @Autowired
    private CommentRepository commentRepository;

    
	
	@Override
    public void save(Comment comment, User user, Report report) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		comment.setId(null);
        comment.setContent(comment.getContent()); 
        comment.setTimeStamp(timestamp);
		comment.setUser(user);
		comment.setReport(report);
        commentRepository.save(comment);
    }
	
	@Override
	public List<Comment> findByReport(Report report){
		return commentRepository.findByReport(report);
	}
	
}