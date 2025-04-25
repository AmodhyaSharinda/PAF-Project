package com.skillsharing.controller.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users/")
public class userControler {

    @GetMapping("/getUser")
    public String getUsers(){
        return "One user";
    }
}
