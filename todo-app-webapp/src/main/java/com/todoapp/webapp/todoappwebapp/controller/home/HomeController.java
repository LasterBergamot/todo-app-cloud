package com.todoapp.webapp.todoappwebapp.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String GET_MAPPING_ROOT = "/";

    private static final String PAGE_INDEX = "index";

    @GetMapping(GET_MAPPING_ROOT)
    public String getHomePage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        LOGGER.info("Showing Home page!");

        boolean userIsLoggedIn = principal != null;

        model.addAttribute("userIsLoggedIn", userIsLoggedIn);

        if (userIsLoggedIn) {

        }

        return PAGE_INDEX;
    }
}

