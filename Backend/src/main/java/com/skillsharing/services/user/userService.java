package com.skillsharing.services.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.user.userDTO;
import com.skillsharing.model.user.user;
import com.skillsharing.repository.user.userRepo;

@Service
@Transactional
public class userService {
    @Autowired
    private userRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<userDTO> getAllUsers(){
        List<user> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<userDTO>>(){}.getType());
    }

    public userDTO createUser(userDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, user.class));
        return userDTO;
    }
}
