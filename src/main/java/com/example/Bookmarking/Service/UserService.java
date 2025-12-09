package com.example.Bookmarking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Bookmarking.DTO.UserDto;
import com.example.Bookmarking.Models.UserModel;
import com.example.Bookmarking.Repository.UserRepository;

@Service
public class UserService{
   
    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Autowired
    private UserRepository userRepository;

    public UserModel save(UserDto userDto) {
        UserModel user = new UserModel(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getFullname());
        return userRepository.save(user);
    }
    
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}