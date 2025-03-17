package com.example.User_Managment_System.repository;

import com.example.User_Managment_System.Entity.Role;
import com.example.User_Managment_System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
