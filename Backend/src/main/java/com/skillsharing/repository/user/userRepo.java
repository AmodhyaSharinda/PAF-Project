package com.skillsharing.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.user.user;

@Repository
public interface userRepo extends MongoRepository<user, Integer>{

}
