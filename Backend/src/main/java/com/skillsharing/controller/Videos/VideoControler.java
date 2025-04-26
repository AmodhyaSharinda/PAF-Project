package com.skillsharing.controller.Videos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsharing.dto.video.videoDTO;
import com.skillsharing.services.video.videoService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/videos/")
public class VideoControler {
    @Autowired
    private videoService videoService;

    @GetMapping("/getvideos")
    public List<videoDTO> getvideos(){
        return videoService.getvideos();
    }

    //not sure ---------------------------------------------------------------------------------------------------------
    @GetMapping("/getvideosby/{userID}")
    public List<videoDTO> getvideosbyID(@PathVariable ObjectId _id){
        return videoService.getvideosbyID(_id);
    }
    //-----------------------------------------------------------------------------------------------------------------

    @PostMapping("/addvideos")
    public videoDTO saveVideo(@RequestBody videoDTO videoDTO){
        return  videoService.createVideo(videoDTO);
    }

    @PutMapping("/updatevideos")
    public videoDTO updateVideo(@RequestBody videoDTO videoDTO){
        return  videoService.updateVideo(videoDTO);
    }

    @DeleteMapping("/deltevideos")
    public String deltevideos(@RequestBody videoDTO videDTO){
        return  videoService.deltevideo(videDTO);
    }
}
