package com.skillsharing.dto.follow;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class followDTO {
    private String _id;
    private String followerId;
    private String followingId;
    private LocalDateTime followedAt;
}
