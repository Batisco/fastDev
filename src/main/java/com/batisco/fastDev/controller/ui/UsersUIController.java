package com.batisco.fastDev.controller.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/users")
public class UsersUIController {

    private static final Logger logger = LoggerFactory.getLogger(UsersUIController.class.getName());


    public UsersUIController() {

    }

    @GetMapping("/getAll")
    public String getAll() {
        return "/templates/users.html";
    }

}
