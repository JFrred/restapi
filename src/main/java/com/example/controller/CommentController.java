package com.example.controller;

import com.example.dto.CommentDto;
import com.example.model.Comment;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentService.getCommentByIds(postId, commentId);
    }


    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable long postId, @RequestBody Comment comment) {
        return commentService.createComment(postId, comment);
    }

    @PutMapping("/{postId}/comments")
    public Comment editComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        return commentService.updateComment(postId, commentDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
    }
}

