package com.skillsharing.services.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.user.loginDTO;
import com.skillsharing.dto.user.userDTO;
import com.skillsharing.model.user.User;
import com.skillsharing.model.user.UserPrinciples;
import com.skillsharing.repository.user.userRepo;

@Service
@Transactional
public class userService implements UserDetailsService {
    
    @Autowired
    private userRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepo.findByUsername(username);

        if(user == null) {
            System.err.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciples(user);
    }

    // Method to get users
    public List<userDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<userDTO>>(){}.getType());
    }

    //create User
    public userDTO createUser(userDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(modelMapper.map(userDTO, User.class));

        return userDTO;
    }

    public String verifyUser(loginDTO loginDTO) {
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return jwtService.genarateToken(loginDTO.getUsername());
        }
        
        return "failed";
    }

}
