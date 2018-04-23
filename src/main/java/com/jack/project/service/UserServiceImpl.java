package com.jack.project.service;

import com.jack.project.model.Role;
import com.jack.project.model.User;
import com.jack.project.repository.RoleRepository;
import com.jack.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {

		if (user.isMentor() == true) {
			Role mentor = roleRepository.findByName("ROLE_MENTOR");
			Set<Role> mentorRoles = new HashSet<>();
			mentorRoles.add(mentor);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(mentorRoles);
			user.setEmail(user.getEmail());
			user.setFirstname(user.getFirstname());
			user.setLastname(user.getLastname());
			user.setSchool(user.getSchool());
			userRepository.save(user);
		} else {
			Role student = roleRepository.findByName("ROLE_STUDENT");
			Set<Role> studentRoles = new HashSet<>();
			studentRoles.add(student);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(studentRoles);
			user.setEmail(user.getEmail());
			user.setUsername(user.getUsername());
			user.setFirstname(user.getFirstname());
			user.setLastname(user.getLastname());
			user.setSchool(user.getSchool());
			user.setGoals(user.getGoals());
			user.setSkills(user.getSkills());
			user.setReports(user.getReports());
			userRepository.save(user);
		}
	}

	@Override
	public void update(User userForm, User user) {

		Role student = roleRepository.findByName("ROLE_STUDENT");
		Set<Role> studentRoles = new HashSet<>();
		studentRoles.add(student);
		user.setRoles(studentRoles);

		user.setPassword(user.getPassword());
		user.setUsername(user.getUsername());
		user.setFirstname(user.getFirstname());
		user.setLastname(userForm.getLastname());
		user.setEmail(user.getEmail());
		user.setReports(user.getReports());
		user.setSkills(user.getSkills());
		user.setGoals(user.getGoals());
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void addStudent(User user, User currentUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByMentor(boolean mentor) {
		return userRepository.findByMentor(mentor);
	}

}