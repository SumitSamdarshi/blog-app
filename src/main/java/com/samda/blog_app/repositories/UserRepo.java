package com.samda.blog_app.repositories;

import java.util.Optional;

import com.samda.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
