package com.jack.project.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jack.project.model.User;


public interface UserService {
    void save(User user);

    User findByUsername(String username);

	void addStudent(User user, User currentUser);

	User findById(int id);


}