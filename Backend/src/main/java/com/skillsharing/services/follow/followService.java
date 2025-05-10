package com.skillsharing.services.follow;

import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
import java.util.Optional;

//import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.follow.followDTO;

import com.skillsharing.model.follow.followModel;

import com.skillsharing.repository.follow.followRepository;

@Service
@Transactional
public class followService {
    @Autowired
    private followRepository followRepository;

    @Autowired
    private ModelMapper modelMapper;

        
    public List<followDTO> getFollowers(String userID) {
        
        List<followModel> followers = followRepository.findByFollowingId(userID);
        return modelMapper.map(followers, new TypeToken<List<followDTO>>(){}.getType());
    }

    public List<followDTO> getfollowing(String userID){
        List<followModel> following = followRepository.findByFollowerId(userID);
        return modelMapper.map(following, new TypeToken<List<followDTO>>(){}.getType());
    }
    
    public followDTO followuser(followDTO followDTO) {
        followRepository.save(modelMapper.map(followDTO, followModel.class));
        return followDTO;
    }

    public followDTO updatefollowtime(followDTO followDTO){
        followRepository.save(modelMapper.map(followDTO, followModel.class));
        return followDTO;
    }

    public String unfollowuser(String id){
        followRepository.deleteById(id);
        return "successfully deleted";
    }

    public long countFollowers(String userId) {
        return followRepository.countByFollowingId(userId);
    }

    public long countFollowing(String userId) {
        return followRepository.countByFollowerId(userId);
    }


    public String unfollowwUser(String followerId, String followingId) {
        Optional<followModel> followOptional = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);

    if (followOptional.isPresent()) {
        followModel follow = followOptional.get(); // ✅ get the FollowModel
        String fid = follow.get_id();              // ✅ get fid separately
        followRepository.deleteById(fid);
       return "Successfully unfollowed user.";
    } else {
       return "Follow relationship not found.";
    }

    }

    
    
}
