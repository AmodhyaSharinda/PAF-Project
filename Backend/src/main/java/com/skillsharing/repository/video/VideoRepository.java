package com.skillsharing.repository.video;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.video.video;

@Repository
public interface VideoRepository extends MongoRepository<video, String> {

    // Find posts by userId (for getting posts of a specific user)
    @Query("{ 'user' : ?0 }")
    List<video> findByUser(ObjectId user);

}