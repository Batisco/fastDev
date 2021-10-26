package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.AddedUserDto;
import com.batisco.fastDev.dto.UserDto;
import com.batisco.fastDev.model.User;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("get all users");
        List<UserDto> response = mapper.mapUsersToDto(userService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<UserDto> getById(@RequestParam UUID id) {
        logger.info("get user by id = " + id);
        UserDto response = mapper.mapUserToDto(userService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody AddedUserDto dto) {
        logger.info("add new user = " + dto);
        User userWithoutId = mapper.mapToUser(dto);
        UserDto response = mapper.mapUserToDto(userService.add(userWithoutId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
