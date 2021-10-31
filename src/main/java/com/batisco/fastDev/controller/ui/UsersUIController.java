package com.batisco.fastDev.controller.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/ui/users")
public class UsersUIController {

    private static final Logger logger = LoggerFactory.getLogger(UsersUIController.class.getName());


    public UsersUIController() {

    }

    @GetMapping("/getAll")
    public String getAll() {
        logger.info("Get form for all users");
        return "/templates/users.html";
    }

    @GetMapping("/getUpdateForm")
    public String getUpdateForm(@RequestParam("id") UUID userId) {
        logger.info("Get update form for user");
        return "/templates/updatedUser.html";
    }

    @GetMapping("/getAddedForm")
    public String getAddedForm() {
        logger.info("Get added form for user");
        return "/templates/addedUser.html";
    }

}
