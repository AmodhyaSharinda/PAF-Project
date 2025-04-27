package com.skillsharing.controller.post;

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

import com.skillsharing.dto.post.postDTO;
import com.skillsharing.services.post.postService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/posts/")
public class postControler {
    @Autowired
    private postService postService;

    @GetMapping("/getposts")
    public List<postDTO> getPosts(){
        return postService.getPosts();
    }

    //not sure ---------------------------------------------------------------------------------------------------------
    @GetMapping("/getpostsby/{userID}")
    public List<postDTO> getpostbyID(@PathVariable ObjectId user){
        return postService.getPostbyID(user);
    }
    //-----------------------------------------------------------------------------------------------------------------

    @PostMapping("/addposts")
    public postDTO savePost(@RequestBody postDTO postDTO){
        return  postService.createPost(postDTO);
    }

    @PutMapping("/updateposts/{id}")
    public postDTO updatePost(@PathVariable ObjectId id, @RequestBody postDTO postDTO){
        return  postService.updatePost(id, postDTO);
    }

    @DeleteMapping("/deltepost/{id}")
    public String deletePost(@PathVariable ObjectId id){
        return  postService.deletePost(id);
    }
}
