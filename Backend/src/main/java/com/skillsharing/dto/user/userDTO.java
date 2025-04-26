package com.skillsharing.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private String id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private int phoneNumber;
    private String age;
    private String gender;
    private String education; 
}
