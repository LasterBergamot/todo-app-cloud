package com.todoapp.webapp.todoappwebapp.client.user;

import com.todoapp.services.user.userservices.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_USERS;
import static com.todoapp.webapp.todoappwebapp.util.Constants.SERVICE_NAME_USER_SERVICES;

@FeignClient(value = SERVICE_NAME_USER_SERVICES)
public interface UserService {

    @GetMapping(REQUEST_MAPPING_USERS)
    ResponseEntity<List<User>> getAllUsers();
}
