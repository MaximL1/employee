package com.project.employee.controller;

import java.security.InvalidParameterException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionInterceptor {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);

    @ExceptionHandler(value = {InvalidParameterException.class})
    protected ResponseEntity<Void> handleInvalidParameterException(InvalidParameterException ex)
    {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
