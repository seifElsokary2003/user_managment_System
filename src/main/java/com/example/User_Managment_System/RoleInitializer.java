package com.example.User_Managment_System;

import com.example.User_Managment_System.Entity.Role;
import com.example.User_Managment_System.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        List<String> defaultRoles = List.of("USER", "ADMIN");

        for (String roleName : defaultRoles) {
            if (roleRepository.findByRoleName(roleName).isEmpty()) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleRepository.save(role);
                System.out.println("✅ Role created: " + roleName);
            }
        }
    }
}
