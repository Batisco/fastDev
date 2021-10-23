package com.batisco.fastDev.controller;

import com.batisco.fastDev.model.exceptions.UserAlreadyHaveProductException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class.getName());


    @ExceptionHandler(value = UserAlreadyHaveProductException.class)
    public ResponseEntity<String> handle(UserAlreadyHaveProductException e) {
        logger.error("User already have product with this name", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
