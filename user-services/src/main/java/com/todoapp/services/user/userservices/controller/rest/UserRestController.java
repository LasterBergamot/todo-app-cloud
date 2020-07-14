package com.todoapp.services.user.userservices.controller.rest;

import com.todoapp.services.user.userservices.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.todoapp.services.user.userservices.util.Constants.GET_MAPPING_HANDLE_USER;
import static com.todoapp.services.user.userservices.util.Constants.GET_MAPPING_USERNAME;
import static com.todoapp.services.user.userservices.util.Constants.KEY_NAME;
import static com.todoapp.services.user.userservices.util.Constants.KEY_USER;
import static com.todoapp.services.user.userservices.util.Constants.PRE_AUTHORIZE_ROLE_USER;

@RestController
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/username")
    public Map<String, Object> username() {
        LOGGER.info("bla");

        return Collections.singletonMap("bla", "bla");
    }

    @GetMapping(GET_MAPPING_USERNAME)
    public Map<String, Object> getUsername(OAuth2User principal) {
        LOGGER.info("Getting username!");

        return Collections.singletonMap(KEY_NAME, userService.getUsername(principal));
    }

    //TODO: should be a POST method
    @PreAuthorize(PRE_AUTHORIZE_ROLE_USER)
    @GetMapping(GET_MAPPING_HANDLE_USER)
    public Map<String, Object> handleUser(OAuth2User principal) {
        LOGGER.info("Handling user!");

        return Collections.singletonMap(KEY_USER, userService.handleUser(principal));
    }
}
