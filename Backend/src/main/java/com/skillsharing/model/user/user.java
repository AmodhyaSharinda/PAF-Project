package com.skillsharing.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    @Id
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
