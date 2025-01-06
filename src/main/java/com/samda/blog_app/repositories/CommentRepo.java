package com.samda.blog_app.repositories;

import com.samda.blog_app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
