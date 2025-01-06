package com.samda.blog_app.services;


import com.samda.blog_app.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

}