package com.example.fileStorage.services;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.dtos.LoginRequestDto;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.models.File;
import com.example.fileStorage.models.User;
import com.example.fileStorage.repositories.FileRepository;
import com.example.fileStorage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileManagementService {

    private final Path rootLocation = Paths.get("uploads");

    private UserRepository userRepository;
    private FileRepository fileRepository;

    @Autowired
    public FileManagementService(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    private void initialization() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
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

    public FileDTO uploadFile(MultipartFile file, LoginRequestDto userRequestDto) throws IOException {
//        checking if the user is valid or not
        Optional<User> userOptional = userRepository.findByUsername(userRequestDto.getUsername());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist.");
        }

        User user = userOptional.get();

//        check if the file is present
        if (file.isEmpty()) {
            throw new FileNotFoundException("Failed to store file.");
        }

        String fileName = file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(fileName).normalize().toAbsolutePath();
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        File uploadedFile = new File();
        uploadedFile.setFileName(fileName);
        uploadedFile.setSize(file.getSize());
        uploadedFile.setContentType(file.getContentType());
        uploadedFile.setUrl(destinationFile.toString());
        uploadedFile.setSize(file.getSize());
        uploadedFile.setUser(user);

        File savedFile = fileRepository.save(uploadedFile);

        FileDTO fileDTO = convertFileTOFileDTO(savedFile);
        return fileDTO;
    }
}
