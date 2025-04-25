package com.skillsharing.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsharing.dto.user.userDTO;
import com.skillsharing.services.user.userService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users/")
public class userControler {

    @Autowired
    private userService userService;

    @GetMapping("/getusers")
    public List<userDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public userDTO saveUser(@RequestBody userDTO userDTO){
        return userService.createUser(userDTO);
    }
}
