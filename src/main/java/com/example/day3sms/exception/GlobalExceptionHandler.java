package com.example.day3sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.example.day3sms.exception.ErrorResponse handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage())
        );

        return new com.example.day3sms.exception.ErrorResponse(
                400,
                "Validation failed",
                errorMap
        );
    }

    // Student not found
    @ExceptionHandler(com.example.day3sms.exception.StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.example.day3sms.exception.ErrorResponse handleStudentNotFound(com.example.day3sms.exception.StudentNotFoundException ex) {

        return new com.example.day3sms.exception.ErrorResponse(
                404,
                ex.getMessage(),
                null
        );
    }
}