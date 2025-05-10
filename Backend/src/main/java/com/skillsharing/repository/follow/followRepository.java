package com.skillsharing.repository.follow;

import java.util.List;
import java.util.Optional;

//import org.bson.types.ObjectId;
//import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.follow.followModel;


@Repository
public interface followRepository extends MongoRepository<followModel, String> {
    // Find all followings by the follower's userId
    List<followModel> findByFollowerId(String followerId);
    List<followModel> findByFollowingId(String followingId);
    Optional<followModel> findByFollowerIdAndFollowingId(String followerId, String followingId);

    // Find posts by userId (for getting posts of a specific user)

    //@Query("{ 'user' : ?0 }")
    //List<followModel> findByFollowerID(String user);
}
