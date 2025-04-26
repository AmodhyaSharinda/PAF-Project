package com.skillsharing.model.post;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class post {
    @Id
    private ObjectId id;
    private ObjectId user;  // The user who created the post
    private String caption;  // The caption of the post
    private String location;  // The location tagged in the post
    private int numberOfLikes;  // Number of likes
    private int numberOfComments;  // Number of comments
    private List<String> photoUrls;  // List of photo URLs
    private List<String> videoUrls;

}
