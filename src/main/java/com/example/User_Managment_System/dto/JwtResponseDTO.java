package com.example.User_Managment_System.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private Long id;
    private String email;
    private List<String> roles;
}
