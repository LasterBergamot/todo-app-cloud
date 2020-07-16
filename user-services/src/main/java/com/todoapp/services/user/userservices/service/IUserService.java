package com.todoapp.services.user.userservices.service;

import com.todoapp.services.user.userservices.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
}
