package com.example.User_Managment_System.service;

import com.example.User_Managment_System.Entity.Role;
import com.example.User_Managment_System.Entity.User;
import com.example.User_Managment_System.dto.AuthResponseDTO;
import com.example.User_Managment_System.dto.LoginRequestDTO;
import com.example.User_Managment_System.dto.RegisterRequestDTO;
import com.example.User_Managment_System.jwt.JwtUtils;
import com.example.User_Managment_System.repository.RoleRepository;
import com.example.User_Managment_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // ✅ تسجيل مستخدم جديد
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }

        Set<Role> roles = getRolesFromRequest(request.getRoles());


        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        // ✅ تحويل User إلى CustomerUserDetails
        CustomerUserDetails userDetails = CustomerUserDetails.BuildCustomerUser(user);

        // ✅ إنشاء كائن Authentication وتمريره لـ generateTokenForUser
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        String token = jwtUtils.generateTokenForUser(authentication);

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ تحويل User إلى CustomerUserDetails
        CustomerUserDetails userDetails = CustomerUserDetails.BuildCustomerUser(user);

        // ✅ إنشاء كائن Authentication وتمريره لـ generateTokenForUser
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        String token = jwtUtils.generateTokenForUser(authentication);

        return new AuthResponseDTO(token);
    }

    private Set<Role> getRolesFromRequest(Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();

        if (roleNames == null || roleNames.isEmpty()) {
            roles.add(roleRepository.findByRoleName("USER")
                    .orElseGet(() -> {
                        Role userRole = new Role();
                        userRole.setRoleName("USER");
                        return roleRepository.save(userRole);
                    }));
        } else {
            roles = roleNames.stream()
                    .map(roleName -> roleRepository.findByRoleName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found")))
                    .collect(Collectors.toSet());
        }

        return roles;
    }


}
