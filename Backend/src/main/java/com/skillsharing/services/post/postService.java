package com.skillsharing.services.post;

import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.post.postDTO;
import com.skillsharing.model.post.post;
import com.skillsharing.repository.post.PostRepository;

@Service
@Transactional
public class postService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

       // Get posts by a specific user
    public List<postDTO> getPosts() {
        List<post> posts = postRepository.findAll();
        return modelMapper.map(posts, new TypeToken<List<postDTO>>(){}.getType());
    }

    //not sure ----------------------------------------------------------------------------------------------------------
    public List<postDTO> getPostbyID(ObjectId userId) {
        List<post> posts = postRepository.findByUser(userId);
        return  modelMapper.map(posts, new TypeToken<List<postDTO>>(){}.getType());
    }
    //-------------------------------------------------------------------------------------------------------------------

     // Create a new post
     public postDTO createPost(postDTO postDTO) {
        postRepository.save(modelMapper.map(postDTO, post.class));
        return postDTO;
    }

     // Create a new post
     public postDTO updatePost(ObjectId id, postDTO postDTO) {
        post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    
        post updatedPost = modelMapper.map(postDTO, post.class);
        updatedPost.setId(existingPost.getId()); // Set the same ID
    
        postRepository.save(updatedPost);
    
        return modelMapper.map(updatedPost, postDTO.class);
    }

    public String deletePost(ObjectId id){
        postRepository.deleteById(id);
        return "successfully deleted";
    }
}
