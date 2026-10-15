package com.example.buoi1.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
               HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body =  ex.getBindingResult().getFieldErrors()
                .stream().collect(Collectors.groupingBy( i -> ((FieldError)i).getField(), Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        return new  ResponseEntity<>(body, headers, status);
    }

}
