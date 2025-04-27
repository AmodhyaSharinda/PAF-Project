package com.skillsharing.controller.follow;

import java.util.List;

//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillsharing.dto.follow.followDTO;
import com.skillsharing.services.follow.followService;

@RestController
@CrossOrigin
@RequestMapping("api/follow")

public class followController {

    @Autowired
    private followService followService;

    // Get all followers of a user
    @GetMapping("/getfollowers/{userID}")
    public List<followDTO> getFollowers(@PathVariable String userID) {
        return followService.getFollowers(userID);
    }

    @GetMapping("/getfollowing/{userID}")
    public List<followDTO> getfollowing(@PathVariable String userID) {
        return followService.getfollowing(userID);
    }
    
    @PostMapping("/followuser")
    public followDTO followuser(@RequestBody followDTO followDTO) {
        return followService.followuser(followDTO);
    }
    
    @PutMapping("/updatefollowtime")
    public followDTO updatefollowtime(@RequestBody followDTO followDTO){
        return followService.updatefollowtime(followDTO);
    }

    @DeleteMapping("/unfollowuser/{id}")
    public String unfollowuser(@PathVariable String id){
        return followService.unfollowuser(id);
    }
   
    @DeleteMapping("/unfollowwuser/{followerid}/{followingid}")
    public String unfollowwUser(@PathVariable String followerid, @PathVariable String followingid){
        return followService.unfollowwUser(followerid, followingid);
    }
}