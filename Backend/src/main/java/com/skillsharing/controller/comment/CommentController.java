package com.skillsharing.controller.comment;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.skillsharing.model.comment.Comment;
import com.skillsharing.services.comment.CommentService;
import com.skillsharing.dto.comment.CommentRequest;


import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.addComment(request));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String postId) {
        return ResponseEntity.ok(commentService.getCommentsForPost(postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id, @RequestBody CommentRequest request) {
        Optional<Comment> updated = commentService.updateComment(id, request);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(403).body("Unauthorized or Comment Not Found");
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
        @PathVariable String id,
        @RequestParam String userId,
        @RequestParam String postOwnerId
    ) {
        boolean deleted = commentService.deleteComment(id, userId, postOwnerId);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.status(403).body("Unauthorized");
    }
}
