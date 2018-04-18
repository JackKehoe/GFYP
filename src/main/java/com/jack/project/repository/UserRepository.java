package com.jack.project.repository;

import com.jack.project.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
	User findById(int id);
	
	User findByMentor(boolean mentor);

	List<User> findByMentorFalse();
}