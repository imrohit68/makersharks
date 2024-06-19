package com.example.makersharksassessment.Services.Implementation;


import com.example.makersharksassessment.DTO.UsersDTO;
import com.example.makersharksassessment.Entity.Users;
import com.example.makersharksassessment.Exception.ResourceNotFoundException;
import com.example.makersharksassessment.Repository.UserRepo;
import com.example.makersharksassessment.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public UsersDTO addUser(UsersDTO usersDTO) {
        usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        return modelMapper.map(userRepo.save(modelMapper.map(usersDTO,Users.class)),UsersDTO.class);

    }

    @Override
    public UsersDTO getUser(String username) {
        return modelMapper.map(userRepo.findById(username).orElseThrow(()-> new ResourceNotFoundException("User","UserId",username)),UsersDTO.class);
    }
}
