package com.skillsharing.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.user.User;

@Repository
public interface userRepo extends MongoRepository<User, String>{

    User findByUsername(String username);

}
