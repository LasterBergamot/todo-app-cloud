package com.todoapp.webapp.todoappwebapp.controller.rest.user;

import com.todoapp.services.user.userservices.model.User;
import com.todoapp.webapp.todoappwebapp.client.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_USERS;

@RestController
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REQUEST_MAPPING_USERS)
    ResponseEntity<List<User>> getAllUsers() {
        LOGGER.info("Getting all Users!");

        return userService.getAllUsers();
    }
}
