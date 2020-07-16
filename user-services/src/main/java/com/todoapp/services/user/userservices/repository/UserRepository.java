package com.todoapp.services.user.userservices.repository;

import com.todoapp.services.user.userservices.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
    User findByGithubId(String githubId);
    User findByGoogleId(String googleId);
}
