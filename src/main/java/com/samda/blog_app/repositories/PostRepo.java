package com.samda.blog_app.repositories;

import java.util.List;

import com.samda.blog_app.entities.Category;
import com.samda.blog_app.entities.Post;
import com.samda.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

}