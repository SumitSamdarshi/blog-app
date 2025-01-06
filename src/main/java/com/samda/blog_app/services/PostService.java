package com.samda.blog_app.services;

import java.util.List;

import com.samda.blog_app.payloads.PostDto;
import com.samda.blog_app.payloads.PostResponse;

public interface PostService {
    // create post

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all post
    PostResponse getAllpost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);

    // get single post
    PostDto getPostById(Integer postId);

    // get post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    // get post by User
    List<PostDto> getPostByUser(Integer userId);

    // search posts
    List<PostDto> searchPosts(String keyword);

}