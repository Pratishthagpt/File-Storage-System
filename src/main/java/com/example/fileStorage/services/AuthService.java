package com.example.fileStorage.services;

import com.example.fileStorage.dtos.LoginRequestDto;
import com.example.fileStorage.dtos.UserDto;
import com.example.fileStorage.exceptions.InvalidPasswordException;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.models.User;
import com.example.fileStorage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto loginUser(String username, String password) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User does not exist. Please enter correct username or signup to register.");
        }

        User user = userOptional.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Username / password does not matches.");
        }

        return UserDto.fromUser(user);
    }
}
