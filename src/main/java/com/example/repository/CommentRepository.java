package com.example.repository;

import com.example.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(long postId);

    @Query("SELECT c FROM Comment c WHERE c.id = ?2 AND c.postId = ?1")
    Comment findCommentByCommentAndPostIds(long postId, long commentId);
}
