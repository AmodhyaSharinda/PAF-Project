package com.skillsharing.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsharing.dto.user.loginDTO;
import com.skillsharing.dto.user.userDTO;
import com.skillsharing.services.user.userService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users/")
public class userControler {

    @Autowired
    private userService userService;

    @GetMapping("/")
    public String getUser(HttpServletRequest request) {
        return "Hello User" + request.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @GetMapping("/getusers")
    public List<userDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public userDTO saveUser(@RequestBody userDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody loginDTO loginDTO) {
        return  userService.verifyUser(loginDTO);
    }
}
