package com.project.employee.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionInterceptor {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ex)
    {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
