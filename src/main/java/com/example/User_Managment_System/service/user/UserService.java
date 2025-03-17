package com.example.User_Managment_System.service.user;

import com.example.User_Managment_System.Entity.User;
import com.example.User_Managment_System.dto.UserUpdateDTO;
import com.example.User_Managment_System.dto.userDto;
import com.example.User_Managment_System.exception.UserNotFoundException;
import com.example.User_Managment_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<userDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    userDto dto = new userDto();
                    dto.setUsername(user.getEmail());
                    dto.setPassword(passwordEncoder.encode(user.getPassword()));
                    return dto;
                }).toList();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new UserNotFoundException("user with id: "+id + " not found!");
                });
    }

    @Override
    public UserUpdateDTO updateUserById(Long id, UserUpdateDTO userUpdateDTO) {
        return userRepository.findById(id)
                .map(userUpdate -> {
                    userUpdate.setFirstName(userUpdateDTO.getFirstName());
                    userUpdate.setLastName(userUpdateDTO.getLastName());
                    userUpdate.setEmail(userUpdateDTO.getEmail());

                    userRepository.save(userUpdate);

                    return new UserUpdateDTO(
                            userUpdate.getFirstName(),
                            userUpdate.getLastName(),
                            userUpdate.getEmail()
                    );
                }).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }


}
