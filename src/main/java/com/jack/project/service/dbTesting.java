package com.jack.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jack.project.model.*;
import com.jack.project.repository.*;

@Transactional
@Service
public class dbTesting {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {

		Role roleStudent = new Role();
		roleStudent.setName("ROLE_STUDENT");
		roleRepository.save(roleStudent);

		Set<GrantedAuthority> grantedAuthoritiesStudent = new HashSet<>();
		grantedAuthoritiesStudent.add(new SimpleGrantedAuthority(roleStudent.getName()));
		
		Role roleMentor = new Role();
		roleMentor.setName("ROLE_MENTOR");
		roleRepository.save(roleMentor);

		Set<GrantedAuthority> grantedAuthoritiesMentor = new HashSet<>();
		grantedAuthoritiesMentor.add(new SimpleGrantedAuthority(roleMentor.getName()));
		
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleStudent);
		HashSet<Role> roles1 = new HashSet<>();
		roles1.add(roleMentor);

		User user = new User();
		String password = "test";
		user.setUsername("jack96");
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEmail("jack@gmail.com");
		user.setFirstname("Jack");
		user.setLastname("Kehoe");
		user.setSchool("Naas CBS");
		user.setMentor(false);
		user.setHasMentor(false);
		
		user.setRoles(roles);
		userRepository.save(user);

		
		User user2 = new User();
		String password2 = "test";
		user2.setUsername("kevin96");
		user2.setPassword(bCryptPasswordEncoder.encode(password2));
		user2.setEmail("kevin@email.com");
		user2.setFirstname("Kevin");
		user2.setLastname("O'Dwyer");
		user2.setSchool("Tallaght Community School");
		user2.setMentor(false);
		
		user2.setRoles(roles);
		userRepository.save(user2);	
		
		User user3 = new User();
		String password3 = "test";
		user3.setUsername("briano");
		user3.setPassword(bCryptPasswordEncoder.encode(password3));
		user3.setEmail("brian@gmail.com");
		user3.setFirstname("Brian");
		user3.setLastname("Walzer");
		user3.setSchool("Swords Secondary School");
		user3.setMentor(false);
		
		user3.setRoles(roles);
		userRepository.save(user3);	
		
		
		User mentor = new User();
		mentor.setUsername("Mentor1");
		mentor.setPassword(bCryptPasswordEncoder.encode(password3));
		mentor.setEmail("mentor@gmail.com");
		mentor.setFirstname("Brian");
		mentor.setLastname("Walzer");
		mentor.setSchool("Naas CBS");
		mentor.setMentor(true);
		mentor.setHasMentor(true);
		
		mentor.setRoles(roles1);
		userRepository.save(mentor);
		

	}

}