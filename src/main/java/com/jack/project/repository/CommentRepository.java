package com.jack.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jack.project.model.Report;
import com.jack.project.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByReport(Report report);

}