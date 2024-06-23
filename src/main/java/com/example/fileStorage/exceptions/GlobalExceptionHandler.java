package com.example.fileStorage.exceptions;

import com.example.fileStorage.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException (
            UserNotFoundException userNotFoundException
    ) {

        return new ResponseEntity<>(
                new ExceptionDTO(HttpStatus.NOT_FOUND, userNotFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
