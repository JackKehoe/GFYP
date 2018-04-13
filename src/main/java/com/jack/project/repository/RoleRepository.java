package com.jack.project.repository;

import com.jack.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findById(int id);
	Role findByName(String name);
}
