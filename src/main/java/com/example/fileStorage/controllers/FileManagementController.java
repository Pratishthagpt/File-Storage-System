package com.example.fileStorage.controllers;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.services.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileManagementController {

    private FileManagementService fileManagementService;

    @Autowired
    public FileManagementController(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    //    Getting all files by username
    @GetMapping("{id}")
    private List<FileDTO> getAllFilesByUser(@PathVariable ("id") Long userId) throws UserNotFoundException {
        return fileManagementService.getAllFilesByUser(userId);
    }
}
