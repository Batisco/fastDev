package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.user.UserRequestDto;
import com.batisco.fastDev.dto.user.UserResponseDto;
import com.batisco.fastDev.model.User;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());


    private final UserService userService;
    private final DtoMapperService mapper;

    @Autowired
    public UserController(UserService userService, DtoMapperService mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        logger.info("Get all users. Parameters: Pageable={}", pageable);
        Page<UserResponseDto> response = mapper.mapUsersToDto(userService.getAll(pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<UserResponseDto> getById(@RequestParam UUID id) {
        logger.info("get user by id = " + id);
        UserResponseDto response = mapper.mapUserToDto(userService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto dto) {
        logger.info("add new user = " + dto);
        User userWithoutId = mapper.mapToUser(dto);
        UserResponseDto response = mapper.mapUserToDto(userService.add(userWithoutId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto dto) {
        logger.info("update user = " + dto);
        User user = mapper.mapToUser(dto);
        UserResponseDto response = mapper.mapUserToDto(userService.update(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
