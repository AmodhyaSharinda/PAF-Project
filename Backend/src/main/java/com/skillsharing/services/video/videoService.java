package com.skillsharing.services.video;

import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.video.videoDTO;
import com.skillsharing.model.video.video;
import com.skillsharing.repository.video.VideoRepository;

@Service
@Transactional
public class videoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ModelMapper modelMapper;

       // Get vidoes by a specific user
    public List<videoDTO> getvideos() {
        List<video> vidoes = videoRepository.findAll();
        return modelMapper.map(vidoes, new TypeToken<List<videoDTO>>(){}.getType());
    }

    //not sure ----------------------------------------------------------------------------------------------------------
    public List<videoDTO> getvideosbyID(ObjectId userId) {
        List<video> posts = videoRepository.findByUser(userId);
        return  modelMapper.map(posts, new TypeToken<List<videoDTO>>(){}.getType());
    }
    //-------------------------------------------------------------------------------------------------------------------

     // Create a new video
     public videoDTO createVideo(videoDTO postDTO) {
        videoRepository.save(modelMapper.map(postDTO, video.class));
        return postDTO;
    }

     // Create a new post
     public videoDTO updateVideo(videoDTO postDTO) {
        videoRepository.save(modelMapper.map(postDTO, video.class));
        return postDTO;
    }

    public String deltevideo(videoDTO posDTO){
        videoRepository.delete(modelMapper.map(posDTO, video.class));
        return "successfully deleted";
    }
}
