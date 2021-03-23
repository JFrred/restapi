package com.example.controller;

import com.example.dto.PostDto;
import com.example.mapper.PostMapper;
import com.example.model.Post;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        return PostMapper.mapPostToDto(postService.getPosts(pageNumber, sort));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        return postService.getPostsWithComments(pageNumber,sort);
    }

    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable long postId) {
        return postService.getPostById(postId);
    }

}