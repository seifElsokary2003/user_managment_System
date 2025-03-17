package com.example.User_Managment_System.service.user;

import com.example.User_Managment_System.Entity.User;
import com.example.User_Managment_System.dto.UserUpdateDTO;
import com.example.User_Managment_System.dto.userDto;

import java.util.List;

public interface IUserService {
    List<userDto> getAllUsers();
    void deleteUserById(Long id);

    UserUpdateDTO updateUserById(Long id, UserUpdateDTO user);
}
