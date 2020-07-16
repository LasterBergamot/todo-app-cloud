package com.todoapp.services.user.userservices.service.impl;

import com.todoapp.services.user.userservices.model.User;
import com.todoapp.services.user.userservices.repository.UserRepository;
import com.todoapp.services.user.userservices.service.IUserService;
import com.todoapp.services.util.utilservices.util.MongoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.todoapp.services.user.userservices.util.Constants.COLLECTION_NAME_USER;
import static com.todoapp.services.user.userservices.util.Constants.INDEX_NAME_USER_GITHUB_ID_INDEX;
import static com.todoapp.services.user.userservices.util.Constants.INDEX_NAME_USER_GOOGLE_ID_INDEX;
import static com.todoapp.services.user.userservices.util.Constants.KEY_GITHUB_ID;
import static com.todoapp.services.user.userservices.util.Constants.KEY_GOOGLE_ID;

@Service
public class UserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final MongoUtil mongoUtil;

    @Autowired
    public UserService(UserRepository userRepository, MongoUtil mongoUtil) {
        this.userRepository = userRepository;
        this.mongoUtil = mongoUtil;
    }

    @PostConstruct
    public void initIndexes() {
        mongoUtil.createIndex("Creating index for the 'github_id' field of User.", COLLECTION_NAME_USER, KEY_GITHUB_ID, INDEX_NAME_USER_GITHUB_ID_INDEX);
        mongoUtil.createIndex("Creating index for the 'google_id' field of User.", COLLECTION_NAME_USER, KEY_GOOGLE_ID, INDEX_NAME_USER_GOOGLE_ID_INDEX);
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Finding all Users!");

        return userRepository.findAll();
    }
}
