package com.example.fileStorage.services;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.models.File;
import com.example.fileStorage.models.User;
import com.example.fileStorage.repositories.FileRepository;
import com.example.fileStorage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileManagementService {

    private UserRepository userRepository;
    private FileRepository fileRepository;

    @Autowired
    public FileManagementService(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    public List<FileDTO> getAllFilesByUser(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with Id - " + userId + " not found.");
        }

        User user = userOptional.get();

        List<File> files = fileRepository.findByUser(user);

        List<FileDTO> fileDTOS = new ArrayList<>();

        for (File file : files) {
            fileDTOS.add(convertFileTOFileDTO(file));
        }
        return fileDTOS;
    }

    private FileDTO convertFileTOFileDTO(File file) {
        FileDTO fileDTO = new FileDTO();

        fileDTO.setId(file.getId());
        fileDTO.setFilename(fileDTO.getFilename());
        fileDTO.setSize(file.getSize());
        fileDTO.setUrl(file.getUrl());
        fileDTO.setContentType(file.getContentType());

        return fileDTO;
    }
}
