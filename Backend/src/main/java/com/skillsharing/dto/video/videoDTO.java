package com.skillsharing.dto.video;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class videoDTO {
    private ObjectId id;
    private ObjectId user;
    private String caption;
    private String location;
    private int numberOfLikes;
    private int numberOfComments;
    private List<String> photoUrls;
    private List<String> videoUrls;

}
