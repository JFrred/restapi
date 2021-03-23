package com.example.service;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // problem with hibernate n + 1, map Post object to DTO
    public List<Post> getPosts(Integer page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    public Post getPostById(long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPosts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllById(ids);

        allPosts.forEach(post -> post.setComments(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long postId) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == postId)
                .collect(Collectors.toList());
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post editedPost = postRepository.findById(post.getId()).orElseThrow(); // fetching post -> first transaction
        editedPost.setTitle(post.getTitle()); // editing post -> second transaction
        editedPost.setContent(post.getContent());
        return editedPost;
    }

    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }
}
