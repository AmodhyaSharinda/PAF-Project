package com.skillsharing.repository.post;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.post.post;

@Repository
public interface PostRepository extends MongoRepository<post, ObjectId> {

    // Find posts by userId (for getting posts of a specific user)
    @Query("{ 'user' : ?0 }")
    List<post> findByUser(ObjectId user);

}