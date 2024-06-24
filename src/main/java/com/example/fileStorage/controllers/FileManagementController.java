package com.example.fileStorage.controllers;

import com.example.fileStorage.dtos.FileDTO;
import com.example.fileStorage.dtos.LoginRequestDto;
import com.example.fileStorage.dtos.SuccessResponseDto;
import com.example.fileStorage.dtos.UserDto;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.services.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<FileDTO> getAllFilesByUser(@PathVariable ("id") Long userId) throws UserNotFoundException {
        return fileManagementService.getAllFilesByUser(userId);
    }

    @PostMapping("/upload")
    public ResponseEntity<SuccessResponseDto> uploadFile (@RequestParam MultipartFile file,
                                                          @RequestBody LoginRequestDto userRequestDto) throws IOException {

        fileManagementService.uploadFile(file, userRequestDto);

        return new ResponseEntity<>(
                new SuccessResponseDto(HttpStatus.OK, "File uploaded successfully")
                , HttpStatus.OK
        );
    }
}
