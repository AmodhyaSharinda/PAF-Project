package com.skillsharing.model.progressPlan;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "progressPlan")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class progressPlan {
    @Id
    private ObjectId id;

    private String title;
    private String description;
    private List<String> videos; 
    private String createdBy; // Admin or system
    private int totalVideos;
    private int estimatedDuration; // In minutes
    private String thumbnailUrl;
    private List<String> tags;
    
}
