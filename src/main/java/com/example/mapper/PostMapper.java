package com.example.mapper;

import com.example.dto.PostDto;
import com.example.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static List<PostDto> mapPostToDto(List<Post> allByPosts) {
        return allByPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    public static PostDto mapToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }

}
