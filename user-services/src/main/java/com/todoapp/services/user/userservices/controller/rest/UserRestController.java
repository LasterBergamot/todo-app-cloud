package com.todoapp.services.user.userservices.controller.rest;

import com.todoapp.services.user.userservices.model.User;
import com.todoapp.services.user.userservices.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.todoapp.services.user.userservices.util.Constants.REQUEST_MAPPING_USERS;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(REQUEST_MAPPING_USERS)
    public ResponseEntity<List<User>> getAllUsers() {
        LOGGER.info("Getting all Users!");

        return ResponseEntity.ok(userService.getAllUsers());
    }
}
