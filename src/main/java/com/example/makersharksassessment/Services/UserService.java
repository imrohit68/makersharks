package com.example.makersharksassessment.Services;

import com.example.makersharksassessment.DTO.UsersDTO;

public interface UserService {
    UsersDTO addUser(UsersDTO usersDTO);
    UsersDTO getUser(String username);
}
