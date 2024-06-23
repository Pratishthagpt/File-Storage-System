package com.example.fileStorage.controllers;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.models.File;
import com.example.fileStorage.models.User;
import com.example.fileStorage.services.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileManagementController {

    private FileManagementService fileManagementService;

    @Autowired
    public FileManagementController(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    //    Getting all files by username
    private List<FileDTO> getAllFilesByUsername(Long userId) {
        return fileManagementService.getAllFilesByUsername(userId);
    }
}
