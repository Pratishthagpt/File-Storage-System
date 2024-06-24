package com.example.fileStorage.services;

import com.example.fileStorage.dtos.UserDto;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.models.User;
import com.example.fileStorage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserDetails(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with Id - " + userId + " not found.");
        }

        User user = userOptional.get();

        return UserDto.fromUser(user);
    }

}
