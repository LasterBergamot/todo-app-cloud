package com.todoapp.services.user.userservices.service;

import com.todoapp.services.user.userservices.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IUserService {

    String getUsername(OAuth2User principal);
    User handleUser(OAuth2User principal);
}
