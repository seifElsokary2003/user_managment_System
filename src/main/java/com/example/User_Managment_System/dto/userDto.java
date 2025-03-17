package com.example.User_Managment_System.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class userDto {
    private String username;
    private String password;
}
