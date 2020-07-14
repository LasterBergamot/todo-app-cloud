package com.todoapp.webapp.todoappwebapp.controller.rest.user;

import com.todoapp.webapp.todoappwebapp.client.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.todoapp.webapp.todoappwebapp.util.Constants.KEY_NAME;
import static com.todoapp.webapp.todoappwebapp.util.Constants.KEY_USER;
import static com.todoapp.webapp.todoappwebapp.util.Constants.PRE_AUTHORIZE_ROLE_USER;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_HANDLE_USER;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_USERNAME;

@RestController
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REQUEST_MAPPING_USERNAME)
    public Map<String, Object> getUsername(@AuthenticationPrincipal OAuth2User principal) {
        LOGGER.info("Getting username!");

        return Collections.singletonMap(KEY_NAME, userService.getUsername(principal));
    }

    //TODO: should be a POST method
    @PreAuthorize(PRE_AUTHORIZE_ROLE_USER)
    @GetMapping(REQUEST_MAPPING_HANDLE_USER)
    public Map<String, Object> handleUser(@AuthenticationPrincipal OAuth2User principal) {
        LOGGER.info("Handling user!");

        return Collections.singletonMap(KEY_USER, userService.handleUser(principal));
    }
}
