package com.example.buoi1.exception;

import com.example.buoi1.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException ex){
//
//        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> businessExceptionHandler(RuntimeException ex){
//
//        ErrorResponse response = new ErrorResponse(ex.getMessage(), 500, LocalDateTime.now());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(Exception ex){

        ErrorResponse response = new ErrorResponse(ex.getMessage(), 500, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
