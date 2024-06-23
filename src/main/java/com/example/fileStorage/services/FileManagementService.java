package com.example.fileStorage.services;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.models.File;
import com.example.fileStorage.models.User;
import com.example.fileStorage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileManagementService {

    private UserRepository userRepository;

    @Autowired
    public FileManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<FileDTO> getAllFilesByUsername(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with Id - " + userId + " not found.");
        }


    }
}
