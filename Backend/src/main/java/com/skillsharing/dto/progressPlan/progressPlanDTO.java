package com.skillsharing.dto.progressPlan;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class progressPlanDTO {
    private ObjectId id;
    
    private String title;
    private String description;
    private List<String> videos; // List of video references
    private String createdBy; // Admin or system
    private int totalVideos;
    private int estimatedDuration; // In minutes
    private String thumbnailUrl;
    private List<String> tags;
    
}