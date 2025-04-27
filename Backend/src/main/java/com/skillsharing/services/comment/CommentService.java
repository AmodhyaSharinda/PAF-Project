package com.skillsharing.services.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.skillsharing.repository.comment.CommentRepository;
import com.skillsharing.model.comment.Comment;
import com.skillsharing.services.comment.CommentService;
import com.skillsharing.dto.comment.CommentRequest;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(CommentRequest request) {
        Comment comment = new Comment();
        comment.setPostId(request.getPostId());
        comment.setUserId(request.getUserId());
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPost(String postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    public Optional<Comment> updateComment(String id, CommentRequest request) {
        return commentRepository.findById(id).map(comment -> {
            if (!comment.getUserId().equals(request.getUserId())) return null;
            comment.setContent(request.getContent());
            comment.setUpdatedAt(LocalDateTime.now());
            return commentRepository.save(comment);
        });
    }

    public boolean deleteComment(String id, String requestingUserId, String postOwnerId) {
        return commentRepository.findById(id).map(comment -> {
            if (comment.getUserId().equals(requestingUserId) || postOwnerId.equals(requestingUserId)) {
                commentRepository.deleteById(id);
                return true;
            }
            return false;
        }).orElse(false);
    }
}

