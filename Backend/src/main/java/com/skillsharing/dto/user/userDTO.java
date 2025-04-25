package com.skillsharing.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private int id;
    private String name;
    private String email; 
}
