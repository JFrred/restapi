package com.example.service;

import com.example.dto.CommentDto;
import com.example.model.Comment;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    public Comment createComment(long postId, Comment comment) {
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(long postId, CommentDto commentDto) {
            Comment commentEdited = commentRepository.findCommentByCommentAndPostIds(postId, commentDto.getId());
            commentEdited.setContent(commentDto.getContent());
            return commentEdited;
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment getCommentByIds(long postId, long commentId) {
        return commentRepository.findCommentByCommentAndPostIds(postId, commentId);
    }
}
