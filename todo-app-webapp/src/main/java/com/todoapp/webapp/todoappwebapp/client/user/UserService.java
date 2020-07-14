package com.todoapp.webapp.todoappwebapp.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import static com.todoapp.webapp.todoappwebapp.util.Constants.PRE_AUTHORIZE_ROLE_USER;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_HANDLE_USER;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_USERNAME;
import static com.todoapp.webapp.todoappwebapp.util.Constants.SERVICE_NAME_USER_SERVICES;

@FeignClient(value = SERVICE_NAME_USER_SERVICES)
public interface UserService {

    @GetMapping(REQUEST_MAPPING_USERNAME)
    Map<String, Object> getUsername(@AuthenticationPrincipal OAuth2User principal);

    @PreAuthorize(PRE_AUTHORIZE_ROLE_USER)
    @GetMapping(REQUEST_MAPPING_HANDLE_USER)
    Map<String, Object> handleUser(@AuthenticationPrincipal OAuth2User principal);
}
