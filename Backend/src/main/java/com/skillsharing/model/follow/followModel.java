package com.skillsharing.model.follow;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor  
@AllArgsConstructor
@Data
public class followModel {
    @Id
    private String _id;
    private String followerId;
    private String followingId;
    private LocalDateTime followedAt;
    
}
