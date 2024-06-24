package com.example.fileStorage.controllers;

import com.example.fileStorage.dtos.LoginRequestDto;
import com.example.fileStorage.dtos.UserDto;
import com.example.fileStorage.exceptions.InvalidPasswordException;
import com.example.fileStorage.exceptions.UserNotFoundException;
import com.example.fileStorage.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequestDto loginRequest) throws UserNotFoundException, InvalidPasswordException {
        UserDto userDto = authService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }



}
